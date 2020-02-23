package com.kasianov.sergei.omaloma.core.extentions

import retrofit2.Response
import java.lang.Exception

/**
 * A generic class that holds a value of network request result.
 * @param <T>
 */
sealed class RequestResult<out R> {

    data class Success<out T>(val data: T) : RequestResult<T>()
    data class Error(val exception: Exception) : RequestResult<Nothing>()

    override fun toString() : String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[message=$exception]"
        }
    }
}

/**
 * `true` if [RequestResult] is of type [RequestResult.Success] and holds
 * non-null [RequestResult.Success.data].
 */
val RequestResult<*>.successed
        get() = this is RequestResult.Success && data != null

/**
 * A generic method,which handles Retrofit2 response, wraps and returns [RequestResult]
 */
suspend fun <T> getRequestResult(call: suspend () -> Response<T>): RequestResult<T> {
    try {
        val response = call()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) return RequestResult.Success(body)
        }else if (response.errorBody() != null) {
            val errorBody = response.errorBody()
            return RequestResult.Error(Exception("error body:  ${errorBody?.string()}"))
        }

        return RequestResult.Error(Exception("${response.code()} ${response.message()}"))
    } catch (e: Exception) {
        return RequestResult.Error(Exception(e.message ?: e.toString()))
    }
}
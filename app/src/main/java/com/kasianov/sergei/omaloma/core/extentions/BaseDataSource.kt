package com.kasianov.sergei.omaloma.core.extentions

import retrofit2.Response

/**
 * Abstract Base Data source class with error handling
 */
/**
 * Abstract Base Data source class with error handling
 */
abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): RequestResult<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return RequestResult.Success(body)
            }else if (response.errorBody() != null) {
                val errorBody = response.errorBody()
                return RequestResult.Error("error body:  ${errorBody?.string()}")
            }

            return RequestResult.Error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return RequestResult.Error(e.message ?: e.toString())
        }
    }

}
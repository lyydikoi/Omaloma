package com.kasianov.sergei.omaloma.core.extentions

/**
 * A generic class that holds a value of network request result.
 * @param <T>
 */
sealed class RequestResult<out R> {

    data class Success<out T>(val data: T) : RequestResult<T>()
    data class Error(val message: String) : RequestResult<Nothing>()

    override fun toString() : String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[message=$message]"
        }
    }
}

/**
 * `true` if [RequestResult] is of type [RequestResult.Success] and holds
 * non-null [RequestResult.Success.data].
 */
val RequestResult<*>.successed
        get() = this is RequestResult.Success && data != null
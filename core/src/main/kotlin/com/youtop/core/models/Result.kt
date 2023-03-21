package com.youtop.core.models

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * A generic class that holds a value.
 * @param <T>
 */
sealed class Result<out R> {

    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val error: Throwable) : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[error=$error]"
        }
    }
}

inline fun <reified T, reified R> Result<T>.map(transform: (T) -> R): Result<R> {
    return when (this) {
        is Result.Success<T> -> Result.Success(transform(this.data))
        is Result.Error -> Result.Error(this.error)
    }
}

inline fun <reified T, reified R> Flow<Result<T>>.mapResultData(
    crossinline transform: suspend (T) -> R
): Flow<Result<R>> {
    return map { result ->
        result.map { data ->
            transform(data)
        }
    }
}

/**
 * Map to ViewState without transformation
 */
fun <R> Result<R>.toViewState(
    isEmpty: (R) -> (Boolean)
): ViewState<R> {
    return toViewState(isEmpty) { it }
}

/**
 * Map to ViewState and apply transformation
 */
fun <T, R> Result<T>.toViewState(
    isEmpty: (T) -> (Boolean),
    transform: (T) -> (R)
): ViewState<R> {
    return when (this) {
        is Result.Success -> {
            if (isEmpty(this.data)) {
                ViewState.Empty
            } else {
                val data = transform(this.data)
                ViewState.Success(data)
            }
        }
        is Result.Error -> {
            val error = this.error.cause ?: this.error
            val errorMessage = error.message
            ViewState.Error(
                errorData = null,
                error = error,
                errorMessage = errorMessage
            )
        }
    }
}

fun <R> Result<R>.toViewState(
): ViewState<R> {
    return toViewStates { it }
}


fun <T, R> Result<T>.toViewStates(
    transform: (T) -> (R)
): ViewState<R> {
    return when (this) {
        is Result.Success -> {
            val data = transform(this.data)
            ViewState.Success(data)
        }

        is Result.Error -> {
            val error = this.error.cause ?: this.error
            val errorMessage = error.message
            ViewState.Error(
                errorData = null,
                error = error,
                errorMessage = errorMessage
            )
        }
    }
}

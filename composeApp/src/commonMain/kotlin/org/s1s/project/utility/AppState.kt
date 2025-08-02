package org.s1s.project.utility

sealed class AppState<out T> {
    data object Idle : AppState<Nothing>()
    data object Loading : AppState<Nothing>()
    data class Success<out T>(val data: T) : AppState<T>()
    data class Error(val errorMsg: String) : AppState<Nothing>()

    fun isLoading(): Boolean = this is Loading
    fun isError(): Boolean = this is Error
    fun isSuccess(): Boolean = this is Success

    fun getSuccessDataOrNull(): T? {
        return if (this is Success) {
            this.data
        } else {
            null
        }
    }

    fun getErrorMessageOrNull(): String? {
        return if (this is Error) {
            this.errorMsg
        } else {
            null
        }
    }
}
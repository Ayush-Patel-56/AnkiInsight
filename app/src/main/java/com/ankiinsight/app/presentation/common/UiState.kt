package com.ankiinsight.app.presentation.common

sealed class UiState<out T> {
    object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val message: String, val code: ErrorCode = ErrorCode.GENERIC) : UiState<Nothing>()
    object Empty : UiState<Nothing>()
}

enum class ErrorCode {
    GENERIC,
    API_KEY_MISSING,
    RATE_LIMITED,
    NO_PERMISSION,
    NOT_INSTALLED
}

package com.example.core.errorhandler

import androidx.annotation.StringRes
import com.example.venues.R
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException

object ErrorHandler {
    fun errorMessage(throwable: Throwable): NetworkError {
        val networkError = when (throwable) {
            is HttpException -> handleHttpErrors(throwable.code())
            is SocketTimeoutException -> NetworkError.Timeout
            is IOException -> NetworkError.NetworkUnavailable
            else -> NetworkError.Unknown
        }
        return networkError
    }

    private fun handleHttpErrors(responseCode: Int?): NetworkError {
        return when (responseCode) {
            HttpURLConnection.HTTP_NOT_FOUND -> NetworkError.NotFound
            HttpURLConnection.HTTP_UNAUTHORIZED -> NetworkError.Unauthorized
            HttpURLConnection.HTTP_FORBIDDEN -> NetworkError.Forbidden
            HttpURLConnection.HTTP_INTERNAL_ERROR -> NetworkError.InternalError
            else -> NetworkError.Unknown
        }
    }
}

sealed class NetworkError(@StringRes val messageId: Int) {
    data object NotFound : NetworkError(R.string.network_error_not_found)
    data object Unauthorized : NetworkError(R.string.network_error_unauthorized)
    data object Forbidden : NetworkError(R.string.network_error_forbidden)
    data object InternalError : NetworkError(R.string.network_error_internal_server_error)
    data object Unknown : NetworkError(R.string.network_error_unknown)
    data object NetworkUnavailable : NetworkError(R.string.network_error_network_unavailable) // New
    data object Timeout : NetworkError(R.string.network_error_timeout)
}
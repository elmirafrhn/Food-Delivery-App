package com.example.venues.utils.error

import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response

internal fun createHttpException(code: Int, errorMessage: String): HttpException {
    val responseBody = ResponseBody.create(null, errorMessage)
    val response = Response.error<Any>(code, responseBody)
    return HttpException(response)
}
package com.ankkumar.hellogold.remote


import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class RetryCallback<T> : Callback<T> {
    private var callCount = 0

    override fun onResponse(call: Call<T>,
                            response: Response<T>) {
        if (response.isSuccessful) {
            when (response.code()) {
                200 -> onReturnSuccessResponse(response.body())
                else -> {
                }
            }

        } else {
            if (callCount < 2) {
                call.clone().enqueue(this)
                callCount += 1
            } else {
                callCount = 0
                onFailureResponse()
            }
        }
    }

    override fun onFailure(call: Call<T>,
                           t: Throwable) {
        if (callCount < 2) {
            call.clone().enqueue(this)
            callCount += 1
        } else {
            callCount = 0
            t.printStackTrace()
            onFailureResponse()
        }
    }

    abstract fun onReturnSuccessResponse(body: T?)
    abstract fun onNeedToCheckResponse(body: T)
    abstract fun onFailureResponse()

    companion object {
        private val TAG = "RetryCallback"
    }
}
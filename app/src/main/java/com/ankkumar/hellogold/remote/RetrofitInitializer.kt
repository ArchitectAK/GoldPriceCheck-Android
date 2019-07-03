package com.ankkumar.hellogold.remote

import com.ankkumar.hellogold.BuildConfig
import com.ankkumar.hellogold.model.request.Register
import com.ankkumar.hellogold.model.response.RegisterResponse
import com.ankkumar.hellogold.model.response.SpotPrice
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInitializer private constructor() {

    private val retrofit: Retrofit

    init {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val builder = OkHttpClient.Builder()
            .connectTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
            .readTimeout(READ_TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
            .writeTimeout(WRITE_TIMEOUT.toLong(), TimeUnit.MILLISECONDS)

        // Logging is only available in debug mode.
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(loggingInterceptor)
        }
        val client = builder.build()

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun createRegister(registration: Register, callback: OnCallback<RegisterResponse>) {
        retrofit.create(RegistrationService::class.java)
            .getRegistration(REGISTER_URL, registration)
            .enqueue(object : RetryCallback<RegisterResponse>() {
                override fun onReturnSuccessResponse(body: RegisterResponse?) {
                    body?.let { callback.onReturn(it) }
                }

                override fun onNeedToCheckResponse(body: RegisterResponse) {
                    callback.onNeedCheck(body)
                }

                override fun onFailureResponse() {
                    callback.onFailed()
                }
            })
    }

    fun createSpotPrice(callback: OnCallback<SpotPrice>) {
        retrofit.create(SpotPriceService::class.java!!).getSpotPrice()
            .enqueue(object : RetryCallback<SpotPrice>() {
                override fun onReturnSuccessResponse(body: SpotPrice?) {
                    body?.let { callback.onReturn(it) }
                }


                override fun onNeedToCheckResponse(body: SpotPrice) {
                    callback.onNeedCheck(body)
                }

                override fun onFailureResponse() {
                    callback.onFailed()
                }
            })
    }

    interface OnCallback<T> {
        fun onReturn(body: T)
        fun onNeedCheck(body: T)
        fun onFailed()
    }

    companion object {
        private val TAG = RetrofitInitializer::class.java!!.getSimpleName()
        private val CONNECTION_TIMEOUT = 5000
        private val READ_TIMEOUT = 5000
        private val WRITE_TIMEOUT = 5000
        private val BASE_URL = "https://staging.hellogold.com/api/"

        private val REGISTER_URL = "v3/users/register.json"

        private var initializer: RetrofitInitializer? = null

        fun getinstance(): RetrofitInitializer {
            if (initializer == null) {
                initializer = RetrofitInitializer()
            }
            return initializer as RetrofitInitializer
        }
    }
}
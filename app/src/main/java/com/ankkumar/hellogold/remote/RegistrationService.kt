package com.ankkumar.hellogold.remote

import com.ankkumar.hellogold.model.request.Register
import com.ankkumar.hellogold.model.response.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Url

interface RegistrationService {
    @POST
    abstract fun getRegistration(
        @Url url: String,
        @Body reg: Register
    ): Call<RegisterResponse>
}
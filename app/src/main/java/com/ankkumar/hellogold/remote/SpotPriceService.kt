package com.ankkumar.hellogold.remote

import com.ankkumar.hellogold.model.response.SpotPrice
import com.ankkumar.hellogold.util.Util.URL_SPOT_PRICE
import retrofit2.Call
import retrofit2.http.GET

public interface SpotPriceService {
    @GET(URL_SPOT_PRICE)
    abstract fun getSpotPrice(): Call<SpotPrice>
}
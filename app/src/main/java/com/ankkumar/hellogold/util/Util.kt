package com.ankkumar.hellogold.util


import android.content.Context
import android.net.ConnectivityManager
import android.util.Patterns
import java.util.*

object Util {
    const val URL_SPOT_PRICE = "v2/spot_price.json"
    fun checkNetworkStatus(ctx: Context): Boolean {
        val connectivityManager = ctx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            ?: return false
        val info = connectivityManager.activeNetworkInfo
        return info != null && info.isConnectedOrConnecting
    }

    fun isEmailFormat(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun generateUUID(): String {
        return UUID.randomUUID().toString()
    }

    fun generateRandom(): String {
        val leftLimit = 97 // letter 'a'
        val rightLimit = 122 // letter 'z'
        val targetStringLength = 64
        val random = Random()
        val buffer = StringBuilder(targetStringLength)
        for (i in 0 until targetStringLength) {
            val randomLimitedInt = leftLimit + (random.nextFloat() * (rightLimit - leftLimit + 1)).toInt()
            buffer.append(randomLimitedInt.toChar())
        }
        return buffer.toString()
    }
}
package com.ankkumar.hellogold.util


import android.content.Context
import android.content.SharedPreferences
import com.ankkumar.hellogold.app.GoldApp


class PreferenceUtil private constructor() {

    private val preferences: SharedPreferences

    init {
        preferences = GoldApp.app!!.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
    }

    fun clear() {
        val p = preferences
        p.edit().clear().apply()
    }

    fun getString(key: String, defValue: String): String? {
        val p = preferences
        return p.getString(key, defValue)
    }

    fun putString(key: String, value: String) {
        val p = preferences
        val editor = p.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getBoolean(key: String, defValue: Boolean): Boolean {
        val p = preferences
        return p.getBoolean(key, defValue)
    }

    fun putBoolean(key: String, value: Boolean) {
        val p = preferences
        val editor = p.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getInteger(key: String, defValue: Int): Int {
        val p = preferences
        return p.getInt(key, defValue)
    }

    fun putInteger(key: String, value: Int) {
        val p = preferences
        val editor = p.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    companion object {
        private val PREFERENCE_NAME = "HELLOGOLD_PREFERENCE"

        val EMAIL_ADDRESS = "EMAIL_ADDRESS"
        val API_TOKEN = "API_TOKEN"
        val PUBLIC_KEY = "PUBLIC_KEY"
        val ACCOUNT_NUMBER = "ACCOUNT_NUMBER"

        private var util: PreferenceUtil? = null

        val instance: PreferenceUtil
            get() {
                if (util == null) {
                    util = PreferenceUtil()
                }
                return util as PreferenceUtil
            }
    }
}
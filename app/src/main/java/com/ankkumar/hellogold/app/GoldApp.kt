package com.ankkumar.hellogold.app

import android.app.Application

class GoldApp : Application() {
    override fun onCreate() {
        super.onCreate()
        app = this
    }

    companion object {
        var app: GoldApp? = null
            private set
    }
}
package com.ankkumar.hellogold.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ankkumar.hellogold.util.PreferenceUtil


class InitRouterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        routing()
    }

    private fun routing() {
        var intent = Intent(this@InitRouterActivity, MainActivity::class.java)
        if (PreferenceUtil.instance.getString(PreferenceUtil.EMAIL_ADDRESS, "") == "") {
            intent = Intent(this@InitRouterActivity, RegisterActivity::class.java)
        }
        startActivity(intent)
        finish()
    }
}
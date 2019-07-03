package com.ankkumar.hellogold.ui


import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ankkumar.hellogold.R
import com.ankkumar.hellogold.databinding.ActivityRegisterBinding


class RegisterActivity : AppCompatActivity() {

    private var binding: ActivityRegisterBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register) as ActivityRegisterBinding?
        binding!!.regViewModel = RegisterViewModel()
    }
}
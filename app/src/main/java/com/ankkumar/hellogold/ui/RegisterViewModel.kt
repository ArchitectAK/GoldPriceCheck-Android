package com.ankkumar.hellogold.ui

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.CompoundButton
import android.widget.Toast
import com.ankkumar.hellogold.model.request.Register
import com.ankkumar.hellogold.model.response.RegisterResponse
import com.ankkumar.hellogold.remote.RetrofitInitializer
import com.ankkumar.hellogold.util.PreferenceUtil
import com.ankkumar.hellogold.util.Util


class RegisterViewModel {

    var registration: Register = Register()

    private val isRegistrationValid: Boolean
        get() = Util.isEmailFormat(registration.email)

    var tncCheckListener: CompoundButton.OnCheckedChangeListener =
        CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            var checked = "false"
            if (isChecked) {
                checked = "true"
            }
            registration.tnc = checked
        }

    fun register(v: View) {
        if (isRegistrationValid) {
            if (Util.checkNetworkStatus(v.context)) {
                registration.uuid = Util.generateUUID()
                registration.data = Util.generateRandom()

                if (registration.tnc == "false") {
                    Toast.makeText(v.context, "Please check that TNC to agree with our policy.", Toast.LENGTH_SHORT)
                        .show()
                    return
                }

                Log.w("VM", registration.toString())

                RetrofitInitializer.getinstance()
                    .createRegister(registration, object : RetrofitInitializer.OnCallback<RegisterResponse> {
                        override fun onReturn(body: RegisterResponse) {
                            when (body.result) {
                                "ok" -> {
                                    PreferenceUtil.instance.putString(PreferenceUtil.EMAIL_ADDRESS, registration.email)
                                    PreferenceUtil.instance.putString(PreferenceUtil.API_TOKEN, body.data!!.apiToken!!)
                                    PreferenceUtil.instance.putString(
                                        PreferenceUtil.PUBLIC_KEY,
                                        body.data!!.publicKey!!
                                    )
                                    PreferenceUtil.instance.putString(
                                        PreferenceUtil.ACCOUNT_NUMBER,
                                        body.data!!.accountNumber!!
                                    )

                                    v.context.startActivity(Intent(v.context, MainActivity::class.java))
                                }

                                "error" -> Toast.makeText(v.context, "Error Happened!!", Toast.LENGTH_SHORT).show()

                                else -> {
                                }
                            }
                        }

                        override fun onNeedCheck(body: RegisterResponse) {}

                        override fun onFailed() {
                            Toast.makeText(v.context, "Please check your network status.", Toast.LENGTH_SHORT).show()
                        }
                    })
            } else {
                Toast.makeText(v.context, "Please check network status!!", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(v.context, "Invalid Information!", Toast.LENGTH_SHORT).show()
        }
    }
}
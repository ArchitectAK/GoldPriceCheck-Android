package com.ankkumar.hellogold.model.request

import android.databinding.BaseObservable
import android.databinding.Bindable
import java.util.*

class Register : BaseObservable {
    @get:Bindable
    var email = ""
    @get:Bindable
    var uuid = ""
    var data = ""
    @get:Bindable
    var tnc = "false"

    constructor(email: String, uuid: String, data: String, tnc: String) {
        this.email = email
        this.uuid = uuid
        this.data = data
        this.tnc = tnc
    }

    constructor()

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o !is Register) return false
        val that = o as Register?
        return email == that!!.email &&
                uuid == that.uuid &&
                data == that.data &&
                tnc == that.tnc
    }

    override fun hashCode(): Int {
        return Objects.hash(email, uuid, data, tnc)
    }

    override fun toString(): String {
        return "Registration{" +
                "email='" + email + '\''.toString() +
                ", uuid='" + uuid + '\''.toString() +
                ", data='" + data + '\''.toString() +
                ", tnc='" + tnc + '\''.toString() +
                '}'.toString()
    }
}
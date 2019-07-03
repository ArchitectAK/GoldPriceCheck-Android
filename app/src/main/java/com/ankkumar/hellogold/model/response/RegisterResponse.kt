package com.ankkumar.hellogold.model.response


import com.google.gson.annotations.SerializedName

import java.util.Objects

class RegisterResponse {

    var result: String? = null
    var data: RegisterData? = null
    var code: String? = null

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o !is RegisterResponse) return false
        val that = o as RegisterResponse?
        return result == that!!.result &&
                data == that.data &&
                code == that.code
    }

    override fun hashCode(): Int {
        return Objects.hash(result, data, code)
    }

    override fun toString(): String {
        return "RegisterResponse{" +
                "result='" + result + '\''.toString() +
                ", data=" + data!!.toString() +
                ", code='" + code + '\''.toString() +
                '}'.toString()
    }

    class RegisterData {
        @SerializedName("api_token")
        var apiToken: String? = null

        @SerializedName("public_key")
        var publicKey: String? = null

        @SerializedName("account_number")
        var accountNumber: String? = null

        override fun equals(o: Any?): Boolean {
            if (this === o) return true
            if (o !is RegisterData) return false
            val that = o as RegisterData?
            return apiToken == that!!.apiToken &&
                    publicKey == that.publicKey &&
                    accountNumber == that.accountNumber
        }

        override fun hashCode(): Int {
            return Objects.hash(apiToken, publicKey, accountNumber)
        }

        override fun toString(): String {
            return "RegisterData{" +
                    "apiToken='" + apiToken + '\''.toString() +
                    ", publicKey='" + publicKey + '\''.toString() +
                    ", accountNumber='" + accountNumber + '\''.toString() +
                    '}'.toString()
        }
    }
}

package com.ankkumar.hellogold.model.response


import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

import com.google.gson.annotations.SerializedName

import java.util.Objects

class SpotPrice {
    var result: String? = null

    var data: Data? = null

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o !is SpotPrice) return false
        val spotPrice = o as SpotPrice?
        return result == spotPrice!!.result && data == spotPrice.data
    }

    override fun hashCode(): Int {
        return Objects.hash(result, data)
    }

    override fun toString(): String {
        return "SpotPrice{" +
                "result='" + result + '\''.toString() +
                ", data=" + data +
                '}'.toString()
    }
}

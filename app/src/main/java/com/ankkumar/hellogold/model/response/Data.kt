package com.ankkumar.hellogold.model.response

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

import com.google.gson.annotations.SerializedName

import java.util.Objects

@Entity(tableName = "priceData")
class Data {

    @ColumnInfo(name = "buy")
    var buy: Double? = null

    @ColumnInfo(name = "sell")
    var sell: Double? = null

    @SerializedName("spot_price")
    @ColumnInfo(name = "spotPrice")
    var spotPrice: Double? = null

    @PrimaryKey
    lateinit var timestamp: String

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o !is Data) return false
        val data = o as Data?
        return buy == data!!.buy &&
                sell == data.sell &&
                spotPrice == data.spotPrice &&
                timestamp == data.timestamp
    }

    override fun hashCode(): Int {
        return Objects.hash(buy, sell, spotPrice, timestamp)
    }

    override fun toString(): String {
        return "Data{" +
                "buy=" + buy +
                ", sell=" + sell +
                ", spotPrice=" + spotPrice +
                ", timestamp='" + timestamp + '\''.toString() +
                '}'.toString()
    }
}

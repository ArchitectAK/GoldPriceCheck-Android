package com.ankkumar.hellogold.db


import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.ankkumar.hellogold.model.response.Data


@Dao
interface PriceDao {
    @get:Query("SELECT * FROM priceData ORDER BY timestamp DESC")
    val all: List<Data>

    @Query("SELECT COUNT(*) from priceData")
    fun countAllSavedData(): Int

    @Insert
    fun insertEachData(data: Data)

    @Insert
    fun insertAll(vararg datas: Data)

    @Delete
    fun delete(data: Data)

}
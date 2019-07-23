package com.ankkumar.hellogold.db


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
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
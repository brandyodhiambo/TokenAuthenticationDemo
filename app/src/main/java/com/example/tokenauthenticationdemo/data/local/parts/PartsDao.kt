package com.example.tokenauthenticationdemo.data.local.parts

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tokenauthenticationdemo.data.local.categories.CustomItemEntity

@Dao
interface PartsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertParts(items: PartsEntity)

    @Query("DELETE FROM parts_table")
    suspend fun deletePart()

    @Query("SELECT * FROM parts_table")
    suspend fun getParts(): PartsEntity?
}
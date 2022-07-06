package com.example.tokenauthenticationdemo.data.local.categories

import androidx.room.*

@Dao
interface ItemsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(items: CustomItemEntity)

    @Query("DELETE FROM custom_items_table")
    suspend fun deleteItem()

    @Query("SELECT * FROM custom_items_table")
    suspend fun getItems(): CustomItemEntity?




}
package com.example.tokenauthenticationdemo.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.tokenauthenticationdemo.data.converters.Converters
import com.example.tokenauthenticationdemo.data.local.categories.CustomItemEntity
import com.example.tokenauthenticationdemo.data.local.categories.ItemsDao
import com.example.tokenauthenticationdemo.data.local.parts.PartsDao
import com.example.tokenauthenticationdemo.data.local.parts.PartsEntity

@TypeConverters(Converters::class)
@Database(entities = [CustomItemEntity::class,PartsEntity::class], version = 2, exportSchema = false)
abstract class ItemDataBase :RoomDatabase(){
    abstract val itemsDao: ItemsDao
    abstract val partsDao:PartsDao
}
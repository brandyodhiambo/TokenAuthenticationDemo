package com.example.tokenauthenticationdemo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.tokenauthenticationdemo.data.converters.Converters

@TypeConverters(Converters::class)
@Database(entities = [CustomItemEntity::class], version = 2, exportSchema = false)
abstract class ItemDataBase :RoomDatabase(){
    abstract val dao:ItemsDao
}
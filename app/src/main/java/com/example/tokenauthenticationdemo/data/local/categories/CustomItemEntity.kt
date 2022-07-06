package com.example.tokenauthenticationdemo.data.local.categories

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tokenauthenticationdemo.models.Category
import com.example.tokenauthenticationdemo.models.Diagram

@Entity(tableName = "custom_items_table")
data class CustomItemEntity(
    val categories: List<Category>,
    val diagram: List<Diagram>,
    val liked:Boolean = false,
    @PrimaryKey(autoGenerate = true) val id: Int? = 0,
)

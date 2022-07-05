package com.example.tokenauthenticationdemo.data.local

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.tokenauthenticationdemo.models.Data
import com.google.gson.annotations.SerializedName

@Entity(tableName = "items_table", indices = [Index(value = ["links"], unique = true)])
data class ItemsEntitiy(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("links")
    val links: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("meta")
    val meta: String,
    @PrimaryKey(autoGenerate = true) val id: Int
)
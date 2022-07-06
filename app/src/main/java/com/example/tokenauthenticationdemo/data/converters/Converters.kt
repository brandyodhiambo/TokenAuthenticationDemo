package com.example.tokenauthenticationdemo.data.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.tokenauthenticationdemo.models.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

@ProvidedTypeConverter
class Converters @Inject constructor(private val gson: Gson) {

    @TypeConverter
    fun fromData(data: Data): String {
        return gson.toJson(data)
    }

    @TypeConverter
    fun toData(str: String): Data {
        return gson.fromJson(str, Data::class.java)
    }

    @TypeConverter
    fun fromCategoryJson(json: String): List<Category> {
        return gson.fromJson<ArrayList<Category>>(
            json,
            object : TypeToken<ArrayList<Category>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toCategoryJson(follow: List<Category>): String {
        return gson.toJson(
            follow,
            object : TypeToken<ArrayList<Category>>() {}.type
        ) ?: "[]"
    }
    @TypeConverter
    fun fromImagesJson(json: String): List<Image> {
        return gson.fromJson<ArrayList<Image>>(
            json,
            object : TypeToken<ArrayList<Image>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toImagesJson(follow: List<Image>): String {
        return gson.toJson(
            follow,
            object : TypeToken<ArrayList<Image>>() {}.type
        ) ?: "[]"
    }
    @TypeConverter
    fun fromTypeJson(json: String): List<String> {
        return gson.fromJson<ArrayList<String>>(
            json,
            object : TypeToken<ArrayList<String>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toTypeJson(follow: List<String>): String {
        return gson.toJson(
            follow,
            object : TypeToken<ArrayList<String>>() {}.type
        ) ?: "[]"
    }

    @TypeConverter
    fun fromPartsJson(json: String): List<Part> {
        return gson.fromJson<ArrayList<Part>>(
            json,
            object : TypeToken<ArrayList<Part>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toPartsJson(follow: List<Part>): String {
        return gson.toJson(
            follow,
            object : TypeToken<ArrayList<Part>>() {}.type
        ) ?: "[]"
    }


    @TypeConverter
    fun fromDiagramJson(json: String): List<Diagram> {
        return gson.fromJson<ArrayList<Diagram>>(
            json,
            object : TypeToken<ArrayList<Diagram>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toDiagramJson(follow: List<Diagram>): String {
        return gson.toJson(
            follow,
            object : TypeToken<ArrayList<Diagram>>() {}.type
        ) ?: "[]"
    }


    @TypeConverter
    fun fromModelJson(json: String): List<Model> {
        return gson.fromJson<ArrayList<Model>>(
            json,
            object : TypeToken<ArrayList<Model>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toModelJson(follow: List<Model>): String {
        return gson.toJson(
            follow,
            object : TypeToken<ArrayList<Model>>() {}.type
        ) ?: "[]"
    }
}
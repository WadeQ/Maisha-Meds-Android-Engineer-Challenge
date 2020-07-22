package com.wadektech.maishamedschallenge.data


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "posts_db")
@JsonClass(generateAdapter = true)
data class Posts(
    @Json(name = "body")
    val body: String,
    @PrimaryKey(autoGenerate = false)
    @Json(name = "id")
    val id: Int,
    @Json(name = "title")
    val title: String,
    @Json(name = "userId")
    val userId: Int
)
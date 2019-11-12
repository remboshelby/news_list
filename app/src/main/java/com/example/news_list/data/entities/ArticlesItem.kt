package com.example.news_list.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import java.io.Serializable

@Entity(
	tableName = "news_table"
)
data class ArticlesItem(
	@Json(name = "publishedAt")
	@ColumnInfo(name = "publishedAt")
	val publishedAt: String?,

	@Json(name = "author")
	@ColumnInfo(name = "author")
	val author: String?,

	@Json(name = "urlToImage")
	@ColumnInfo(name = "urlToImage")
	val urlToImage: String?,

	@Json(name = "description")
	@ColumnInfo(name = "description")
	val description: String?,

	@Json(name = "title")
	@ColumnInfo(name = "title")
	val title: String?,

	@Json(name = "url")
	@PrimaryKey
	@ColumnInfo(name = "url")
	val url: String,

	@Json(name = "content")
	@ColumnInfo(name = "content")
	val content: String?
) :Serializable

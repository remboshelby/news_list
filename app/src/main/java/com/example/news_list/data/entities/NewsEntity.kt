package com.example.news_list.data.entities

import com.squareup.moshi.Json
import java.io.Serializable

data class NewsEntity(
	@Json(name = "totalResults")
	val totalResults: Int?,
	@Json(name = "articles")
	val articles: List<ArticlesItem>,
	@Json(name = "status")
	val status: String?
): Serializable

package com.jmoreno.superpoderespractica.model

data class ResultComics(
    val code: Int,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    val etag: String,
    val data: DataClassComics
)

data class DataClassComics(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<Comics>
)


data class Comics(
    val id: Long,
    val title: String,
)


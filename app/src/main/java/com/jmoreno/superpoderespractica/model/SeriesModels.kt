package com.jmoreno.superpoderespractica.model

data class Empty(
    val code: Int,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    val etag: String,
    val data: DataClassSeries
)

data class DataClassSeries(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<ResultSeries>
)


data class ResultSeries(
    val id: Long,
    val title: String,
    val description: String?,
    val startYear: Int?,
    val endYear: Int?,
    val modified: String?,
    val thumbnail: Thumbnail?
)


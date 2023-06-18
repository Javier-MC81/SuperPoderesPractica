package com.jmoreno.superpoderespractica.model



data class Welcome(
    val code: Int,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    val etag: String,
    val data: DataClass
)

data class DataClass(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<Heroe>
)


data class Heroe(
    val id: Long,
    val name: String,
    val modified: String,
    val thumbnail: Thumbnail
)

data class Thumbnail(
    val path: String,
    val thumbnailExtension: String = "jpg"
)



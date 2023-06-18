package com.jmoreno.superpoderespractica.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jmoreno.superpoderespractica.model.Thumbnail

@Entity(tableName = "superheros")
data class LocalHero(
    @PrimaryKey @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "thumbnail") val thumbnail: String
)

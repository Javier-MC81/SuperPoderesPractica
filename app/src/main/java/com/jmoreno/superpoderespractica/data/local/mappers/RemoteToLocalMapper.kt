package com.jmoreno.superpoderespractica.data.local.mappers

import com.jmoreno.superpoderespractica.data.local.model.LocalHero
import com.jmoreno.superpoderespractica.model.Heroe
import com.jmoreno.superpoderespractica.model.Thumbnail
import javax.inject.Inject

class RemoteToLocalMapper @Inject constructor(){
    fun mapGetHeroResponse(getHerosResponses: List<Heroe>): List<LocalHero> {
        return getHerosResponses.map { mapGetHeroResponse(it) }
    }
    fun mapGetHeroResponse(getHerosResponse: Heroe): LocalHero {
        return LocalHero(getHerosResponse.id, getHerosResponse.name, "${getHerosResponse.thumbnail.path}.${getHerosResponse.thumbnail.thumbnailExtension}",false)
    }
}
package com.jmoreno.superpoderespractica.utils

import com.jmoreno.superpoderespractica.data.local.model.LocalHero
import com.jmoreno.superpoderespractica.model.DataClassSeries
import com.jmoreno.superpoderespractica.model.Empty
import com.jmoreno.superpoderespractica.model.Heroe
import com.jmoreno.superpoderespractica.model.ResultSeries
import com.jmoreno.superpoderespractica.model.Thumbnail


fun generateHeros(size: Int): List<Heroe> {
    return (0 until size).map { Heroe(it.toLong(), "Name $it","$it",Thumbnail("$it",".jpg")) }
}

/*
fun generateGetHerosResponse(size: Int): List<GetHerosResponse> {
    return (0 until size).map { GetHerosResponse("ID $it", "Name $it", "Description $it", "Photo $it") }
}
*/
fun generateRandomHero(int: Int): LocalHero {
    return LocalHero(int.toLong(), "Name $int", "$int", false)
}
fun generateLocalHero(id: Long): LocalHero {
    return LocalHero(id, "Name $id", "$id",true)
}

fun generateLocalCloneHero(hero: LocalHero): LocalHero {
    return hero
}
fun generateSeriesResponse(size: Int, id: Long): List<ResultSeries> {
    return (0 until size).map { ResultSeries( it.toLong(), "Name $size series of $id superhero", "Description $it", it,it,"$it",
        Thumbnail("$it",".jpg")
    ) }
}
fun generateDataClassSeries(id: Long): DataClassSeries {
    return DataClassSeries(1,1,1,1, generateSeriesResponse(20,id))
}
fun generateEmptyClass(id: Long): Empty {
    return Empty(1,"1","1","1","1","1",generateDataClassSeries(id))
}


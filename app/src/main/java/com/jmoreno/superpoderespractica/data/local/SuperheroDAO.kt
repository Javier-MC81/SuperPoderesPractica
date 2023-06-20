package com.jmoreno.superpoderespractica.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.jmoreno.superpoderespractica.data.local.model.LocalHero
import kotlinx.coroutines.flow.Flow

@Dao
interface SuperheroDAO {
    @Query("SELECT * FROM superheros")
    suspend fun getAll(): List<LocalHero>

    @Query("SELECT * FROM superheros")
    fun getAllFlow(): Flow<List<LocalHero>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllList(users: List<LocalHero>)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHero(users: LocalHero)
    @Query("SELECT * FROM superheros WHERE id LIKE :id")
    fun getHero(id:Long): LocalHero

    @Query("SELECT * FROM superheros WHERE id LIKE :id")
    fun getHeroFlow(id:Long): Flow<LocalHero>


    /*@Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllVararg(vararg users: LocalSuperhero)

    @Delete
    suspend fun delete(user: LocalSuperhero)*/
}
package dev.kurama.k_simpledex.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface PokemonDao {

    @Query("SELECT * FROM poke ORDER BY id DESC")
    fun getAllPokemon(): List<LocalPokemon>

    @Query("SELECT COUNT(*) FROM poke")
    fun countPokemon(): LiveData<Int>

}
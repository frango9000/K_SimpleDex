package dev.kurama.k_simpledex.data

import androidx.lifecycle.LiveData

class PokemonRepository(private val pokemonDao: PokemonDao) {

//    val pokemon : List<LocalPokemon> = pokemonDao.getAllPokemon()

    fun count(): LiveData<Int> = pokemonDao.countPokemon()
}
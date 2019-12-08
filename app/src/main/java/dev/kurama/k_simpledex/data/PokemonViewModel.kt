package dev.kurama.k_simpledex.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class PokemonViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: PokemonRepository

//    val pokemon : List<LocalPokemon>

    init {
        val pokemonDao = PokemonRoom.getDatabase(application).getPokemonDao()
        repository = PokemonRepository(pokemonDao)
//        pokemon = repository.pokemon
    }

    fun count(): LiveData<Int> {
        return repository.count()
    }
}
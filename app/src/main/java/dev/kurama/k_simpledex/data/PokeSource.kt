package dev.kurama.k_simpledex.data

import android.os.AsyncTask
import android.util.Log
import dev.kurama.k_simpledex.SplashActivity
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient
import me.sargunvohra.lib.pokekotlin.model.Pokemon
import java.lang.ref.WeakReference
import java.util.*
import kotlin.math.max

object PokeSource {
    val pokeApi: PokeApiClient = PokeApiClient()

    var ITEMS: MutableList<Pokemon> = ArrayList()
    val ITEM_MAP: MutableMap<Int, Pokemon> = HashMap()


    private fun addItem(item: Pokemon) {
        ITEMS.add(item)
        ITEM_MAP[item.id] = item
    }

    class GetPokemonTask : AsyncTask<Int, Void, Pokemon>() {

        private val pokeApi: PokeApiClient = PokeApiClient()

        override fun doInBackground(vararg p0: Int?): Pokemon {
            return pokeApi.getPokemon(p0[0]!!)
        }

        override fun onPostExecute(result: Pokemon) {
            super.onPostExecute(result)
            addItem(result)
        }
    }

}


class InitPokemonTask(val weakContext: WeakReference<SplashActivity>) : AsyncTask<Int, Int, MutableList<Pokemon>>() {
    val pokeApi: PokeApiClient = PokeApiClient()
    override fun doInBackground(vararg p0: Int?): MutableList<Pokemon> {
        val maxNum: Int? = p0[0]
        val pokemonList: MutableList<Pokemon> = ArrayList()
        maxNum?.let {
            val percent: Int = max((maxNum / 100), 1)
            for (id in 1..maxNum) {
                pokemonList.add(pokeApi.getPokemon(id))
                if (id % percent == 0)
                    publishProgress(id)
            }
        }
        return pokemonList
    }

    override fun onProgressUpdate(vararg values: Int?) {
        weakContext.get()?.progressBarUpdate(values[0]!!)
        Log.d(this.javaClass.name, values[0].toString())
    }

    override fun onPostExecute(result: MutableList<Pokemon>) {
        result.let {
            PokeSource.ITEMS = result
        }

        weakContext.get()?.notifyDoneFetching(result.size)
        Thread.sleep(2000)
        weakContext.get()?.notifySplashEnd()
    }
}

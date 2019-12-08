package dev.kurama.k_simpledex

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dev.kurama.k_simpledex.data.PokemonViewModel
import kotlinx.android.synthetic.main.activity_splash.*
import org.jetbrains.anko.startActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var pokemonViewModel: PokemonViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //val initTask = InitPokemonTask(WeakReference(this)).execute(resources.getInteger(maxPokeToLoad))

        Log.d("Create", "O")

        pokemonViewModel = ViewModelProvider(this).get(PokemonViewModel::class.java)
        pokemonViewModel.count().observe(this, Observer { count ->
            textProgress2.text = count.toString()
            if (count > 963)
                notifySplashEnd()
        })
        //insertP(pokemonDao, LocalPokemon(808, "ZZZ", 40, 60))
        Log.d("Splash Data Path", pokemonViewModel.count().value.toString())

    }

    fun progressBarUpdate(status: Int) {
        progressBar.setProgress(status, true)
        textProgress.text = String.format(getString(R.string.progres_text_format), status)
    }

    fun notifyDoneFetching(size: Int) {
        progressBarUpdate(size)
        textProgress2.text = getString(R.string.loading_complete)
    }

    fun notifySplashEnd() {
        startActivity<MainActivity>()
    }

}

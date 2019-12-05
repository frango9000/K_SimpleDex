package dev.kurama.k_simpledex

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.kurama.k_simpledex.data.InitPokemonTask
import kotlinx.android.synthetic.main.activity_splash.*
import org.jetbrains.anko.startActivity
import java.lang.ref.WeakReference

class SplashActivity : AppCompatActivity() {

    val maxPokeToLoad: Int = R.integer.max_pokemon_to_load
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val initTask = InitPokemonTask(WeakReference(this)).execute(resources.getInteger(maxPokeToLoad))
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

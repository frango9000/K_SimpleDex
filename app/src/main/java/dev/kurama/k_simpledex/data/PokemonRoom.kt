package dev.kurama.k_simpledex.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(LocalPokemon::class), version = 1, exportSchema = true)
abstract class PokemonRoom : RoomDatabase() {

    abstract fun getPokemonDao(): PokemonDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: PokemonRoom? = null

        fun getDatabase(context: Context): PokemonRoom {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PokemonRoom::class.java,
                    "pokedex_db"
                ).createFromAsset("database/db.sqlite3")
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}
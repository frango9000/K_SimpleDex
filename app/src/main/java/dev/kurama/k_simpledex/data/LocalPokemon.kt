package dev.kurama.k_simpledex.data

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "poke")
data class LocalPokemon(
    @PrimaryKey @NonNull val id: Int,
    val name: String,
    val height: Int,
    val weight: Int
)
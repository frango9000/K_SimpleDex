package dev.kurama.k_simpledex.ui.home


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.kurama.k_simpledex.R
import dev.kurama.k_simpledex.ui.home.PokedexHomeFragment.OnListFragmentInteractionListener
import kotlinx.android.synthetic.main.fragment_pokedexhome_list_element.view.*
import me.sargunvohra.lib.pokekotlin.model.Pokemon

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class MyPokedexHomeRecyclerViewAdapter(
    private val mValues: List<Pokemon>,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<MyPokedexHomeRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Pokemon
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_pokedexhome_list_element, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mPokemonId.text = item.id.toString()
        holder.mPokemonName.text = item.name.capitalize()
        holder.mPokemonTypes.text = "Types"

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {

        val mPokemonId: TextView = mView.textViewPokemonId
        val mPokemonName: TextView = mView.textViewPokemonName
        val mPokemonTypes: TextView = mView.textViewPokemonTypes

        override fun toString(): String {
            return super.toString() + " '" + mPokemonId.text + "' '" + mPokemonName + "'"
        }
    }
}

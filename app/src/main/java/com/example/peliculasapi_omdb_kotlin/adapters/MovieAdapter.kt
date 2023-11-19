package com.example.peliculasapi_omdb_kotlin.adapters
import android.content.Intent
import android.net.Uri
import android.text.method.LinkMovementMethod
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.peliculasapi_omdb_kotlin.MovieInfo
import com.example.peliculasapi_omdb_kotlin.R

class MovieAdapter(private val movieList: List<MovieInfo>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.tvTitulo)
        val year: TextView = itemView.findViewById(R.id.tvYear)
        val genre: TextView = itemView.findViewById(R.id.tvGenre)
        val actors: TextView = itemView.findViewById(R.id.tvActors)
        val language: TextView = itemView.findViewById(R.id.tvLanguage)
        val poster: TextView = itemView.findViewById(R.id.tvPoster)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false) // Corregir la referencia a item_movie.xml
        return MovieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentItem = movieList[position]
        holder.title.text = "Title: ${currentItem.title}"
        holder.year.text = "Year: ${currentItem.year}"
        holder.genre.text = "Genre: ${currentItem.genre}"
        holder.actors.text = "Actors: ${currentItem.actors}"
        holder.language.text = "Language: ${currentItem.language}"
        // holder.poster.text = "Poster: ${currentItem.poster}"
        holder.poster.apply {
            text = "Poster: ${currentItem.poster}"
            movementMethod = LinkMovementMethod.getInstance() // Habilitar la acción de hacer clic en enlaces
            setOnClickListener {
                val url = currentItem.poster
                if (Patterns.WEB_URL.matcher(url).matches()) {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    it.context.startActivity(intent)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}

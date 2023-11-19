package com.example.peliculasapi_omdb_kotlin;
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.peliculasapi_omdb_kotlin.adapters.MovieAdapter
import com.example.peliculasapi_omdb_kotlin.data.OMDBService
import com.example.peliculasapi_omdb_kotlin.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var omdbService: OMDBService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvTitulo.text = "~Películas~"
        //  Intanciamos el servicio retrofit
        omdbService = Retrofit.Builder()
            .baseUrl("https://www.omdbapi.com/")
            .addConverterFactory(GsonConverterFactory.create()) // Convierte de JSON a objeto
            .build().create(OMDBService::class.java)  // Genera una clase a partir de la interfaz

        binding.btnBuscar.setOnClickListener {
            // Formateo mi codigo para  'evitar' espacios
            val movieTitle = binding.etBuscar.text.toString().replace(" ", "+")
            val apiKey = "c473c74f"

            omdbService.getMovieInfo(apiKey, movieTitle).enqueue(object : Callback<MovieInfo> {
                override fun onResponse(call: Call<MovieInfo>, response: Response<MovieInfo>) {
                    if (response.isSuccessful) {
                        val movieInfo = response.body()
                        movieInfo?.let {

                            val movieList = listOf(movieInfo)
                            val adapter = MovieAdapter(movieList)
                            // Configurar el RecyclerView para mostrar la información
                            binding.rvPeliculas.adapter = adapter
                            binding.rvPeliculas.layoutManager = LinearLayoutManager(this@MainActivity)

                        }
                    } else {
                        Toast.makeText(this@MainActivity, "Error: ${response.message()}", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<MovieInfo>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Error de conexión o solicitud fallida: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }

    }
}

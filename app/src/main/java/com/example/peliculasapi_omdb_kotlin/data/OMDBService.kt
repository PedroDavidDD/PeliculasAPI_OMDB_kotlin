package com.example.peliculasapi_omdb_kotlin.data
import com.example.peliculasapi_omdb_kotlin.MovieInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OMDBService {
    @GET("/")
    fun getMovieInfo(
        @Query("apikey") apiKey: String,
        @Query("t") movieTitle: String
    ): Call<MovieInfo> // MovieInfo es la clase que representa la respuesta de la API
}

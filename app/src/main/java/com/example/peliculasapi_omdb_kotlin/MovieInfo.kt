package com.example.peliculasapi_omdb_kotlin

import com.google.gson.annotations.SerializedName

data class MovieInfo(
    @SerializedName("Title") val title: String,
    @SerializedName("Year") val year: String,
    @SerializedName("Genre") val genre: String,
    @SerializedName("Language") val language: String,
    @SerializedName("Poster") val poster: String,
    @SerializedName("Actors") val actors: String
)

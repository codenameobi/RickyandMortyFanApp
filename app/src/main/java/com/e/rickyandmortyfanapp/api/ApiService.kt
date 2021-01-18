package com.e.rickyandmortyfanapp.api

import com.e.rickyandmortyfanapp.model.Characters
import retrofit2.http.GET

interface ApiService {
    @GET("api/character/")
    suspend fun searchCharacters(): Characters


}
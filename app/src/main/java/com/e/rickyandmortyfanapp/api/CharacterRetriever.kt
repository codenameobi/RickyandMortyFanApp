package com.e.rickyandmortyfanapp.api

import com.e.rickyandmortyfanapp.model.Characters
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CharacterRetriever {
    private val service: ApiService

    companion object {
        const val BASE_URL = "https://rickandmortyapi.com/"
    }

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(ApiService::class.java)
    }

    suspend fun getCharacters(): Characters {
        return service.searchCharacters()
    }
}
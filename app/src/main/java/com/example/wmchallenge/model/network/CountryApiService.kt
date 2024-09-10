package com.example.wmchallenge.model.network

import com.example.wmchallenge.model.data.Country
import retrofit2.Response
import retrofit2.http.GET

interface CountryApiService {

    @GET("countries.json")
    suspend fun getCountries() : Response<List<Country>>
}
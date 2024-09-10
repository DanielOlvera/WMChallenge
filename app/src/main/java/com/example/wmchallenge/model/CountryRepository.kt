package com.example.wmchallenge.model

import com.example.wmchallenge.model.network.CountryApiService

class CountryRepository (private val apiService: CountryApiService) {

    suspend fun getCountries() = apiService.getCountries()
}

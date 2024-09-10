package com.example.wmchallenge.view.state

import com.example.wmchallenge.model.data.Country

sealed class CountryViewState {
    data object Waiting : CountryViewState()
    data object Loading : CountryViewState()
    data class Success(val countries : List<Country>?) : CountryViewState()
    data class Error(val error: String) : CountryViewState()
}
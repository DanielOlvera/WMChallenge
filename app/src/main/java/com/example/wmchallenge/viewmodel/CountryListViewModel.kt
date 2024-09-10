package com.example.wmchallenge.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wmchallenge.intent.DataIntent
import com.example.wmchallenge.model.CountryRepository
import com.example.wmchallenge.view.state.CountryViewState
import kotlinx.coroutines.launch

class CountryListViewModel(private val repository: CountryRepository): ViewModel() {
    val state: MutableLiveData<CountryViewState> = MutableLiveData(CountryViewState.Waiting)

    fun handleIntent(intent: DataIntent) {
        when(intent) {
            is DataIntent.FetchData -> getCountries()
        }
    }

    private fun getCountries() {
        viewModelScope.launch {
            state.value = CountryViewState.Loading
            try {
                val response = repository.getCountries()
                if (response.isSuccessful) {
                    Log.d("Dang", "getCountries: ${response.body()}")
                    response.body().let {
                        state.value = CountryViewState.Success(it)
                    }
                } else {
                    state.value = CountryViewState.Error("Error: ${response.message()}")
                }
            } catch (e: Exception) {
                state.value = CountryViewState.Error("Exception: ${e.message}")
            }
        }
    }

}
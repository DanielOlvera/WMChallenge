package com.example.wmchallenge.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wmchallenge.R
import com.example.wmchallenge.databinding.ActivityMainBinding
import com.example.wmchallenge.intent.DataIntent
import com.example.wmchallenge.model.CountryRepository
import com.example.wmchallenge.model.network.ApiServiceClient
import com.example.wmchallenge.view.state.CountryViewState
import com.example.wmchallenge.viewmodel.CountryListViewModel
import com.example.wmchallenge.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: CountryListViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = CountryRepository(ApiServiceClient.apiService)
        val factory = ViewModelFactory {CountryListViewModel(repository)}

        viewModel = ViewModelProvider(this, factory)[CountryListViewModel::class.java]

        observeViewModel()

        viewModel.handleIntent(DataIntent.FetchData)
    }

    private fun observeViewModel() {
        viewModel.state.observe(this) { state ->
            when(state) {
                is CountryViewState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.countriesRv.visibility = View.VISIBLE
                    binding.error.visibility = View.GONE
                }
                is CountryViewState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.countriesRv.visibility = View.VISIBLE
                    binding.error.visibility = View.GONE

                    Log.d("Dang", "observeViewModel: Success")
                    Log.d("Dang", "observeViewModel: ${state.countries}")

                    binding.countriesRv.layoutManager = LinearLayoutManager(this)
                    binding.countriesRv.adapter = state.countries?.let { CountryAdapter(it, this) }
                }
                is CountryViewState.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.countriesRv.visibility = View.GONE
                    binding.error.visibility = View.VISIBLE

                    binding.error.text = state.error
                }
                is CountryViewState.Waiting -> {
                    Toast.makeText(this, "Waiting for response!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}
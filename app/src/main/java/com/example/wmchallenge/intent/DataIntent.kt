package com.example.wmchallenge.intent

sealed class DataIntent {
    data object FetchData : DataIntent()
}
package com.example.wmchallenge.model.data

import com.google.gson.annotations.SerializedName


data class Language (

	@SerializedName("code") val code : String,
	@SerializedName("name") val name : String
)
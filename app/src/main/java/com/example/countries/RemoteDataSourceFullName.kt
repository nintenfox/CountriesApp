package com.example.countries

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


interface RestCountriesApiFull {
    @GET("name/{name}?fullText=true")
    suspend fun getCountryByName(@Path("name") cityName: String): List<Country>
}

var retrofitFull = Retrofit.Builder()
    .baseUrl("https://restcountries.com/v2/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()


var restCountriesApiFull = retrofitFull.create(RestCountriesApiFull::class.java)
package com.example.countries

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


interface RestCountriesApiFull {
    @GET("name/{name}?fullText=true")
    suspend fun getCountryByName(@Path("name") countryName: String): List<Country>
}

var retrofitFull = Retrofit.Builder()
    .baseUrl("http://restcountries.com/v3.1/")
    .addConverterFactory(MoshiConverterFactory.create())
    .build()


var restCountriesApiFull = retrofitFull.create(RestCountriesApiFull::class.java)
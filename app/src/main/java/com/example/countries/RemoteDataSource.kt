package com.example.countries

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface RestCountriesApi {
    @GET("name/{name}")
    suspend fun getCountryByName(@Path("name") cityName: String): List<Country>
}

var retrofit = Retrofit.Builder()
    .baseUrl("http://restcountries.com/v3.1/")
    .addConverterFactory(MoshiConverterFactory.create())
    .build()


var restCountriesApi = retrofit.create(RestCountriesApi::class.java)
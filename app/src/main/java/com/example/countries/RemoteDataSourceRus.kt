package com.example.countries

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface RestCountriesApiRus {
    @GET("translation/{translation}")
    suspend fun getCountryByTranslation(@Path("translation") cityName: String): List<Country>
}

var retrofitRus = Retrofit.Builder()
    .baseUrl("http://restcountries.com/v3.1/")
    .addConverterFactory(MoshiConverterFactory.create())
    .build()


var restCountriesApiRus = retrofitRus.create(RestCountriesApiRus::class.java)
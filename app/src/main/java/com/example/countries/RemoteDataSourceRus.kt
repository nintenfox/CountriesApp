package com.example.countries

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface RestCountriesApiTranslated {
    @GET("translation/{translation}")
    suspend fun getCountryByTranslation(@Path("translation") cityName: String): List<Country>
}

var retrofitRus = Retrofit.Builder()
    .baseUrl("http://restcountries.com/v3.1/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()


var restCountriesApiTranslated = retrofitRus.create(RestCountriesApiTranslated::class.java)
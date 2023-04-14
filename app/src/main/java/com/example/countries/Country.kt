package com.example.countries

data class Country(
    val name: String,
    val capital: String,
    val population: Long,
    val area: Long,
    val languages: List<Language>,
    val flag: String,
    val callingCodes: List<String>
)

data class Language(val name: String)

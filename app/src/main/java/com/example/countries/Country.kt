package com.example.countries

data class Country(
    val name: List<String>,
    val capital: List<String>,
    val population: Long,
    val area: Long,
    val languages: List<Language>,
    val flagUrl: String,
    val callingCodes: List<String>
)

data class Language(val name: String)

package com.example.countries

data class Country(
    val name: Name,
    val capital: List<String>,
    val population: Long,
    val area: Long,
    val languages: Map<String, String>,
    val flags: Flag
)

data class Name(
    val common: String,
    val official: String,
    val nativeName: NativeName
)
data class NativeName(
    val official: String,
    val common: String
)
data class Flag(
    val png: String,
    val svg: String,
    val alt: String
)
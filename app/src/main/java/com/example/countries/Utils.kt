package com.example.countries

import android.widget.ImageView
import coil.ComponentRegistry
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import coil.request.ImageRequest
import java.text.NumberFormat
import java.util.*

fun languageConvert(languages: List<Language>): String {
        return languages.joinToString { it.name }
    }

fun numberConvert(number: Long): String {
    return NumberFormat.getInstance(Locale.FRANCE).format(number)
}

fun callingCodeConvert(callingCodes: List<String>): String {
    return "+" + callingCodes[0]
}

suspend fun loadSvg(imageView: ImageView, url: String) {
    if (url.lowercase(Locale.ENGLISH).endsWith("svg")) {
        val imageLoader = ImageLoader.Builder(imageView.context)
            .components(fun ComponentRegistry.Builder.() {
                add(SvgDecoder.Factory())
            })
            .build()
        val request = ImageRequest.Builder(imageView.context)
            .data(url)
            .target(imageView)
            .build()
        imageLoader.execute(request)
    } else {
        imageView.load(url)
    }
}
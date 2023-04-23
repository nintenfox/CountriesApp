package com.example.countries

import android.widget.ImageView
import coil.ComponentRegistry
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import coil.request.ImageRequest
import java.text.NumberFormat
import java.util.Locale

fun nameConvert(name: Name): String {
    return name.common
}

fun capitalConvert(capital: List<String>): String {
    return capital[0]
}

fun languageConvert(languages: Map<String, String>): String {
    return languages.values.joinToString { it }
}

fun numberConvert(number: Long): String {
    return NumberFormat.getInstance(Locale.FRANCE).format(number)
}

fun flagConvert(flags: Flag): String {
    return flags.svg
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

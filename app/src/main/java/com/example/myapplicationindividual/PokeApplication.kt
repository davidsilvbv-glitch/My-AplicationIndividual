package com.example.myapplicationindividual

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory

class PokeApplication : Application(), ImageLoaderFactory {
    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .respectCacheHeaders(false)
            .build()
    }
}

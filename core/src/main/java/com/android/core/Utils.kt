package com.android.core

import android.content.Context
import coil.ImageLoader
import coil.disk.DiskCache
import java.io.File


fun imageLoadWithDiskCache(context: Context): ImageLoader {

    return ImageLoader(context).newBuilder().diskCache {
        DiskCache.Builder().directory(File(context.cacheDir,"img_path"))
            .maxSizeBytes(0.02.toLong()).build()
    }.build()
}
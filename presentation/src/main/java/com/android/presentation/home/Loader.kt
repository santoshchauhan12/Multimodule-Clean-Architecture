package com.android.presentation.home

import com.android.core.ILoader

class Loader: ILoader {
    override fun getComposeMessage(str: String): String {
        return str+ "page"
    }
}
package com.android.presentation.home

import com.android.core.IComposeImp

class ComposeImp: IComposeImp {
    override fun getComposeMessage(str: String): String {
        return str+ "page"
    }
}
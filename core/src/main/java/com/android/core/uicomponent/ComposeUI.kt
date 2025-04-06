package com.android.core.uicomponent

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.zIndex
import com.android.core.ILoader
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject


@Composable
fun showCircularProgress() {

    val context = LocalContext.current

    val composeInstance = remember {
       getComposeEntryPointInstance(context)
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .zIndex(1f)
        .background(color = Color.Black)
        .alpha(0.5f)
        , contentAlignment = Alignment.Center) {

        Text(text = composeInstance.getComposeStr("Loading"), color = Color.Green)
    }
}

fun getComposeEntryPointInstance(context: Context): ComposeInstance {

    var entryPoint = EntryPointAccessors.
    fromApplication(context.applicationContext,
        entryPoint = ComponentInstanceEntryPoint::class.java)

    return entryPoint.getComponentInstanceEntry()
}

class ComposeInstance @Inject constructor(
    private var composeStr : ILoader

){

    fun getComposeStr(msg: String): String {
       return  composeStr.getComposeMessage(msg)
    }
}

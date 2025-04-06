package com.android.core.uicomponent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex


@Composable
fun showCircularProgress() {

    Box(modifier = Modifier
        .fillMaxSize()
        .zIndex(1f).background(color = Color.Black).alpha(0.5f)
        , contentAlignment = Alignment.Center) {

        Text(text = "Loading", color = Color.Green)
    }

}
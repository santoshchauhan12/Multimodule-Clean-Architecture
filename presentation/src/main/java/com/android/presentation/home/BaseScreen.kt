package com.android.presentation.home

import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.android.core.uicomponent.BottomSheet
import com.android.network.ErrorModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun BaseComposableScreen(viewModel: BaseViewModel) {


    var bottomSheetMsg by remember {
        mutableStateOf<String?>(null)
    }

    LaunchedEffect(key1 = Unit ) {

        viewModel.errorStateFlow.collectLatest { failure ->

            failure?.let {
                bottomSheetMsg = (it as ErrorModel.HttpFailure).msg
            }
        }
    }

    bottomSheetMsg?.let {
        BottomSheet(message = it,
            onDissmiss = {
                bottomSheetMsg = null
            })
    }
}
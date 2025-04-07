package com.android.presentation.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.android.core.imageLoadWithDiskCache
import com.android.core.uicomponent.showCircularProgress
import com.android.domain.models.ProductDomainModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asSharedFlow


@Composable
fun HomeScreen() {

    val homeViewModel: HomeViewModel  = hiltViewModel()

    val productState by homeViewModel.productStateFlow.collectAsState()

    LaunchedEffect(key1 = Unit) {
        homeViewModel.getProductList()
    }

    BaseComposableScreen(viewModel = homeViewModel)

    when(productState) {

        ProductUiState.Loading -> {
            showCircularProgress()
        }

        is ProductUiState.Product -> {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                itemsIndexed(
                    (productState as ProductUiState.Product).data,
                    key = {index, item-> item.id.hashCode()}) { index, product->
                    productItem(item = product)
                }
            }
        }

        is ProductUiState.Failure -> {

        }
    }

}


@Composable
fun productItem(item: ProductDomainModel) {

    Log.d("thumbnailimage======", "received ${item.thumbnail}")
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current).data(item.thumbnail)
//            .diskCachePolicy(CachePolicy.ENABLED) // Enable disk caching
//            .memoryCachePolicy(CachePolicy.ENABLED) // work on real device
            .build()

//        imageLoader = imageLoadWithDiskCache(LocalContext.current) // work on real device
    )
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {

        val (productImage, productTitle) = createRefs()

        Image(painter = painter,
            contentDescription = "thumbnail",
            modifier = Modifier
                .size(300.dp)
                .clip(RoundedCornerShape(10.dp))
                .constrainAs(productImage) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                })

//        Image(painter = painter,
//            contentDescription = "thumbnail image",
//            modifier = Modifier
//                .size(200.dp)
//                .constrainAs(productImage) {
//                    top.linkTo(parent.top)
//                    start.linkTo(parent.start)
//                    end.linkTo(parent.end)
//                })


        Text(text = "${item.title}", modifier = Modifier.constrainAs(productTitle) {
            top.linkTo(productImage.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)

        })


    }
}
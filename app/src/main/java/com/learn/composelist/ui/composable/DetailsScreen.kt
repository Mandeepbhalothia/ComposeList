package com.learn.composelist.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import coil.transform.GrayscaleTransformation
import coil.transform.RoundedCornersTransformation
import com.learn.composelist.R
import com.learn.composelist.viewmodel.MainViewModel

@Composable
fun DetailsScreen(
    navController: NavController,
    id: Int,
    viewModel: MainViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Details Screen")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Default.ArrowBackIosNew, "Back Button")
                    }
                }
            )
        }
    ) {
        val chat = viewModel.chatList.value.find { it.id == id }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val imagePainter = rememberImagePainter(data = chat?.imageUrl,
                builder = {
                    placeholder(R.drawable.ic_image_placeholder)
                    error(R.drawable.ic_error)
                    crossfade(500)
                    transformations(
                        GrayscaleTransformation(),
                        RoundedCornersTransformation(12f)
                    )
                })
            Image(
                painter = imagePainter,
                contentDescription = "Image",
                modifier = Modifier.width(200.dp)
                    .height(200.dp)
            )
        }
    }
}
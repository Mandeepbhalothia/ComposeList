package com.learn.composelist.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
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
                .padding(16.dp)
        ) {

            ProfileImage(
                imageUrl = chat?.imageUrl,
                modifier = Modifier
                    .width(200.dp)
                    .height(200.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            MessageText(
                message = "${chat?.lastMessage} ${chat?.lastMessage}"
            )
        }
    }
}

@Composable
fun ProfileImage(
    imageUrl: String?,
    modifier: Modifier
) {
    val imagePainter = rememberImagePainter(data = imageUrl,
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
        modifier = modifier
    )
}

@Composable
fun MessageText(
    message: String
) {
    val style = SpanStyle(
        color = MaterialTheme.colors.primary,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold
    )
    val annotatedString = remember {
        buildAnnotatedString {
            pushStyle(style = style.copy(color = Color.Gray))
            append("Message:")
            pop()
            pushStyle(style = style)
            append(" $message")
            pop()
        }
    }

    Text(
        text = annotatedString,
        color = MaterialTheme.colors.primary,
        fontSize = 16.sp,
        modifier = Modifier.padding(12.dp),
        fontWeight = FontWeight.Bold
    )
}
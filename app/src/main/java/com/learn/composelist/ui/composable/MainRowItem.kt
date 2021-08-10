package com.learn.composelist.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import coil.transform.GrayscaleTransformation
import com.learn.composelist.R
import com.learn.composelist.model.Chat

@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun RowItem(
    chat: Chat,
    onClick: (Int) -> Unit
) {
    Card(
        onClick = {
            onClick(chat.id)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, top = 8.dp, end = 24.dp, bottom = 8.dp),
        elevation = 8.dp,
        shape = RoundedCornerShape(12.dp),
        backgroundColor = Color.White

    ) {
        Row(
            modifier = Modifier.padding(start = 12.dp, end = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val imagePainter = rememberImagePainter(
                data = chat.imageUrl,
                builder = {
                    placeholder(R.drawable.ic_image_placeholder)
                    error(R.drawable.ic_error)
                    transformations(
                        GrayscaleTransformation(),
                        CircleCropTransformation()
                    )
                    crossfade(1000)
                }
            )
            val imagePainterState = imagePainter.state
            Image(
                painter = imagePainter,
                contentDescription = chat.name,
                modifier = Modifier
                    .size(width = 120.dp, height = 150.dp)
                    .align(alignment = Alignment.CenterVertically),
            )
            if (imagePainterState is ImagePainter.State.Loading) {
                CircularProgressIndicator()
            }

            Spacer(modifier = Modifier.width(18.dp))

            Column {
                Text(
                    text = chat.name,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = chat.time,
                    color = Color.Gray,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = chat.lastMessage ?: "No Message",
                    color = Color.Gray,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalCoilApi
@Preview
@Composable
fun RowPreview() {
//    RowItem(chat = DummyData.listChat[0])
}
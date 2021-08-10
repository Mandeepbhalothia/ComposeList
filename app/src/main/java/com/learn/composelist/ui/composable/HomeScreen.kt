package com.learn.composelist.ui.composable

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.learn.composelist.viewmodel.MainViewModel

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {
    Box(modifier = Modifier.fillMaxSize()) {
        val chatList = viewModel.chatList.value
        LazyColumn {
            items(chatList) {
                Log.d("HomeScreen", "Item: $it")
                RowItem(chat = it, onClick = { id ->
                    navController.navigate("item_details_screen/$id")
                })
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

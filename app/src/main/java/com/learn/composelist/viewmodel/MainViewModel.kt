package com.learn.composelist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learn.composelist.model.Chat
import com.learn.composelist.model.DummyData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {

    private val _chatList = MutableStateFlow<List<Chat>>(emptyList())
    val chatList: StateFlow<List<Chat>> get() = _chatList

    init {
        getChatDummyList()
    }

    fun getChatDummyList() {
        viewModelScope.launch {
            _chatList.value = DummyData.listChat
        }
    }
}

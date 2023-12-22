package com.example.composevsxml

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _viewModelState = mutableStateOf(listOf<DataModel>())
    val viewModelState = _viewModelState

    private val _viewModelStateFlow = MutableStateFlow(listOf<DataModel>())
    val viewModelStateFlow = _viewModelStateFlow.asStateFlow()

    init {
        appendData()
    }

    fun appendData(){
        viewModelScope.launch(Dispatchers.IO) {
            val list = mutableListOf<DataModel>()
            list.addAll(viewModelState.value)
            list.addAll(
                DataModel.generateDummyDataList(500)
            )
            _viewModelState.value = list
            _viewModelStateFlow.value = list
        }
    }

    fun deleteData(it: DataModel) {
        viewModelScope.launch(Dispatchers.IO) {
            val list = mutableListOf<DataModel>()
            list.addAll(viewModelState.value)
            list.remove(it)
            _viewModelState.value = list
            _viewModelStateFlow.value = list
        }
    }

    fun addItemToPosition(position: Int, item: DataModel) {

        Log.d("MainViewModel", "addItemToPosition: $position")

        viewModelScope.launch(Dispatchers.IO) {
            val list = mutableListOf<DataModel>()
            list.addAll(viewModelState.value)

            val finalPosition = if (position < list.size) position else list.size

            list.add(finalPosition, item)
            _viewModelState.value = list
            _viewModelStateFlow.value = list
        }
    }
}
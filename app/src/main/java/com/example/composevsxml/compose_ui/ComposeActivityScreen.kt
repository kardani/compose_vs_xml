package com.example.composevsxml.compose_ui

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.composevsxml.DataModel
import com.example.composevsxml.compose_ui.componenets.ComposeBottomSheet
import com.example.composevsxml.compose_ui.componenets.ItemsLazyColumn
import com.example.composevsxml.compose_ui.componenets.ListItemLarge
import com.example.composevsxml.compose_ui.componenets.ListItemSmall
import kotlinx.collections.immutable.ImmutableList

@Composable
fun ComposeActivityScreen(
    state: ImmutableList<DataModel>,
    onDeleteClick: (DataModel) -> Unit,
    onAddItem: (position: Int, item: DataModel) -> Unit
) {
    val selectedItem = remember { mutableStateOf<DataModel?>(null) }

    val listState = rememberLazyListState()



    if (selectedItem.value != null) {
        ComposeBottomSheet(
            item = selectedItem.value!!,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ){
            selectedItem.value = null
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {

        ItemsLazyColumn(
            list = state,
            listState = listState,
            showBottomLoading = listState.canScrollForward,
            onItemClick = {
                selectedItem.value = it
            },
            onDeleteClick = onDeleteClick
        )

        IconButton(
            onClick = {
                Log.d("ComposeActivityScreen", "ComposeActivityScreen: ${listState.firstVisibleItemIndex}")
                val newId = state.maxBy { it.id.toInt() }.id.toInt() + 1

                onAddItem(
                    listState.firstVisibleItemIndex + 1,
                    DataModel.generateDummyData(id = newId)
                )

            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(24.dp)
                .background(Color.White, CircleShape)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                tint = Color.Black,
                contentDescription = null
            )
        }

    }
}













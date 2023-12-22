package com.example.composevsxml.compose_ui.componenets

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composevsxml.DataModel
import kotlinx.collections.immutable.ImmutableList

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemsLazyColumn(
    modifier: Modifier = Modifier,
    list: ImmutableList<DataModel>,
    listState: LazyListState = rememberLazyListState(),
    showBottomLoading: Boolean = false,
    onItemClick: (DataModel) -> Unit,
    onDeleteClick: (DataModel) -> Unit
) {

    Log.d("ItemsLazyColumn", "showBottomLoading: $showBottomLoading")

    LazyColumn(
        state = listState,
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
        ,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        items(
            list,
            key = { item -> item.id } // Use the item id as the key
        ){

            val listItemsModifier: Modifier = Modifier
                .animateItemPlacement()

            if(it is DataModel.Large){
                ListItemLarge(
                    modifier = listItemsModifier,
                    item = it,
                    onItemClick = onItemClick,
                    onDeleteClick = onDeleteClick
                )
            }else if(it is DataModel.Small) {
                ListItemSmall(
                    modifier = listItemsModifier,
                    item = it,
                    onItemClick = onItemClick,
                    onDeleteClick = onDeleteClick
                )
            }
        }

    }
}
package com.example.composevsxml.compose_ui.componenets

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.composevsxml.DataModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComposeBottomSheet(
    modifier: Modifier = Modifier,
    item: DataModel,
    onDismiss: () -> Unit = { }
) {
    val bottomSheetState = rememberModalBottomSheetState(true)

    ModalBottomSheet(
        sheetState = bottomSheetState,
        onDismissRequest = onDismiss
    ) {
        when(item){
            is DataModel.Large -> {
                ListItemLarge(
                    modifier = modifier,
                    item = item,
                )
            }
            is DataModel.Small -> {
                ListItemSmall(
                    modifier = modifier,
                    item = item
                )
            }
        }
    }
}
package com.example.composevsxml.compose_ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.composevsxml.MainViewModel
import com.example.composevsxml.ui.theme.ComposeVsXMLTheme
import kotlinx.collections.immutable.toImmutableList

class ComposeActivity : ComponentActivity() {

    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val state by viewModel.viewModelStateFlow.collectAsState()

            ComposeVsXMLTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ComposeActivityScreen(
                        state.toImmutableList(),
                        onDeleteClick = { viewModel.deleteData(it) },
                        onAddItem = { position, item -> viewModel.addItemToPosition(position, item) }
                    )
                }
            }
        }
    }
}
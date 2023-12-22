package com.example.composevsxml

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composevsxml.compose_ui.ComposeActivity
import com.example.composevsxml.ui.theme.ComposeVsXMLTheme
import com.example.composevsxml.xml_ui.XMLActivity

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeVsXMLTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainActivityScreen()
                }
            }
        }
    }

    @Composable
    private fun MainActivityScreen() {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            Column(
                modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = {
                    startActivity(Intent(this@MainActivity, ComposeActivity::class.java) )
                }) {
                    Text(text = "Compose")
                }

                Spacer(modifier = Modifier.height(50.dp))

                Button(onClick = {
                    startActivity(Intent(this@MainActivity, XMLActivity::class.java) )
                }) {
                    Text(text = "XML")
                }
            }

        }

    }

}
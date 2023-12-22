package com.example.composevsxml.compose_ui.componenets

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun RatingBar(
    maxRating: Int = 5,
    initialRating: Float = 0f,
    onRatingChanged: (Float) -> Unit
) {

    Row(
        modifier = Modifier
    ) {
        for (i in 1..maxRating) {
            val tint = when {
                initialRating >= i -> Color.Yellow
                else -> Color.LightGray
            }

            Icon(
                imageVector = Icons.Rounded.Star,
                tint = tint,
                contentDescription = null
            )
        }
    }
}
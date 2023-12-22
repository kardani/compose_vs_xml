package com.example.composevsxml.compose_ui.componenets

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.composevsxml.DataModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ListItemLarge(
    modifier: Modifier = Modifier,
    item: DataModel,
    onItemClick: (DataModel) -> Unit = {},
    onDeleteClick: (DataModel) -> Unit = {}
) {
    Log.d("ListItem", "ListItem: $item")
    Card(
        modifier = modifier.clickable {
            onItemClick(item)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            GlideImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .background(Color.DarkGray),
                model = item.userImageUrl,
                contentScale = ContentScale.FillWidth,
                contentDescription = "Header Image",
            )
            Spacer(modifier = Modifier.height(8.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                Text(
                    text = "${item.id}_${item.userFullName}",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Completed ${item.completedChallenges} of ${item.totalChallenges} challenges",
                    style = MaterialTheme.typography.bodySmall,
                )
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RatingBar(
                        initialRating = item.rate,
                        onRatingChanged = { }
                    )

                    Spacer(
                        modifier = Modifier
                            .defaultMinSize(minWidth = 16.dp)
                            .weight(1f)
                    )

                    DeleteButton {
                        onDeleteClick(item)
                    }
                }
            }


        }
    }
}
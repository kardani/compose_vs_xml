package com.example.composevsxml.compose_ui.componenets

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.composevsxml.DataModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ListItemSmall(
    modifier: Modifier = Modifier,
    item: DataModel,
    onItemClick: (DataModel) -> Unit = {},
    onDeleteClick: (DataModel) -> Unit = {}
) {
    Log.d("ListItem", "ListItem: $item")
    Card(
        modifier = modifier.clickable {
            onItemClick(item)
        },

        ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
        ) {

            GlideImage(
                modifier = Modifier
                    .size(100.dp),
                model = item.userImageUrl,
                contentScale = ContentScale.FillWidth,
                contentDescription = "Header Image",
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .height(100.dp)
                    .weight(1f)
                    .align(Alignment.Top)
                    .padding(vertical = 8.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                Text(text = "${item.id}_${item.userFullName}", style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold
                ))

                Spacer(modifier = Modifier.height(8.dp))

                Text(text = "Completed ${item.completedChallenges} of ${item.totalChallenges} challenges", style = MaterialTheme.typography.bodySmall)

                Spacer(modifier = Modifier.weight(1f))

                RatingBar(
                    initialRating = item.rate,
                    onRatingChanged = { }
                )

            }

            DeleteButton {
                onDeleteClick(item)
            }

        }
    }

}
package com.podcastchallenge.application.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.podcastchallenge.R
import com.podcastchallenge.ui.theme.PodcastChallengeTheme

@Composable
fun PodcastItem(
    thumbnailUrl: String,
    title:String,
    publisher: String,
    isFavorite: Boolean,
    onClick: () -> Unit
){
    Column(
        Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            PodcastAsyncImage(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(12.dp)),
                imageUrl = thumbnailUrl,
                contentScale = ContentScale.Crop,
                contentDescription = stringResource(R.string.accessibility_podcast_image, title)
            )

            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = publisher,
                    fontSize = 15.sp,
                    color = Color.Gray,
                    fontStyle = FontStyle.Italic,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    modifier = if (isFavorite) Modifier else Modifier.clearAndSetSemantics { },
                    text = stringResource(R.string.favourited),
                    fontSize = 15.sp,
                    color = if (isFavorite) Color.Red else Color.Transparent,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
        HorizontalDivider(modifier = Modifier.padding(start = 16.dp), color = Color.LightGray)
    }
}

@Composable
private fun PodcastItemPreview(isFavorite: Boolean){
    PodcastChallengeTheme {
        PodcastItem(
            thumbnailUrl = "",
            title = "Title",
            publisher = "Publisher",
            isFavorite = isFavorite,
            onClick = { }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PodcastItemFavoritePreview(){
    PodcastItemPreview(isFavorite = true)
}

@Preview(showBackground = true)
@Composable
private fun PodcastItemNotFavoritePreview(){
    PodcastItemPreview(isFavorite = false)
}
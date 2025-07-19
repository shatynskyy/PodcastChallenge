package com.podcastchallenge.application.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.podcastchallenge.R
import com.podcastchallenge.ui.theme.PodcastChallengeTheme

@Composable
fun PodcastTopAppBar(
    title: String? = null,
    onNavigationIcon: (() -> Unit)? = null
){
    Row(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxWidth()
            .height(48.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (onNavigationIcon != null){
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    onClick = onNavigationIcon
                ) {
                    Icon(
                        painter = painterResource(R.drawable.back_arrow),
                        contentDescription = stringResource(R.string.accessibility_back_button)
                    )
                }
                Text(
                    text = stringResource(R.string.back),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        if (title.isNullOrEmpty().not()){
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = title.orEmpty(),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }

}


@Preview(showBackground = true)
@Composable
fun PodcastTopAppTitlePreview(){
    PodcastChallengeTheme {
        PodcastTopAppBar(
            title = "Podcasts"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PodcastTopAppBackPreview(){
    PodcastChallengeTheme {
        PodcastTopAppBar(
            onNavigationIcon = { }
        )
    }
}
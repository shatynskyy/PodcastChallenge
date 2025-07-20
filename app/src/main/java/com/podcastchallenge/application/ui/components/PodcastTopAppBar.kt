package com.podcastchallenge.application.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.podcastchallenge.R
import com.podcastchallenge.application.ui.theme.PodcastChallengeTheme

@Composable
fun PodcastTopAppBar(
    title: String? = null,
    onNavigationIconClick: (() -> Unit)? = null
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .statusBarsPadding()
            .height(48.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (onNavigationIconClick != null){
            val backButtonContentDescription = stringResource(R.string.accessibility_back_button)
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .clickable { onNavigationIconClick() }
                    .semantics {
                        contentDescription = backButtonContentDescription
                        role = Role.Button
                    },
                verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    painter = painterResource(R.drawable.back_arrow),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier.clearAndSetSemantics {  },
                    text = stringResource(R.string.back),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        if (title.isNullOrEmpty().not()){
            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .semantics{ heading() },
                text = title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }

}


@Preview(showBackground = true)
@Composable
private fun PodcastTopAppTitlePreview(){
    PodcastChallengeTheme {
        PodcastTopAppBar(
            title = "Podcasts"
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PodcastTopAppBackPreview(){
    PodcastChallengeTheme {
        PodcastTopAppBar(
            onNavigationIconClick = { }
        )
    }
}
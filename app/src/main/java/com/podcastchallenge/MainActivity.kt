package com.podcastchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.podcastchallenge.application.navigation.PodcastNavigation
import com.podcastchallenge.application.navigation.Screen
import com.podcastchallenge.application.ui.theme.PodcastChallengeTheme
import com.podcastchallenge.application.ui.theme.SetSystemBarIcons
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PodcastChallengeTheme {
                SetSystemBarIcons(darkIcons = true)
                Surface(
                    Modifier.fillMaxSize()
                ) {
                    val navController = rememberNavController()
                    PodcastNavigation(
                        navController = navController,
                        startDestination = Screen.PodcastList.route
                    )
                }
            }
        }
    }
}
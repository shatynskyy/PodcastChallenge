package com.podcastchallenge.application.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.podcastchallenge.application.screens.podcastdetail.PodcastDetailScreen
import com.podcastchallenge.application.screens.podcastslist.PodcastListScreen

@Composable
fun PodcastNavigation(
    navController: NavHostController,
    startDestination: String
){
    NavHost(
        navController = navController,
        startDestination = startDestination
    ){
        composable(route = Screen.PodcastsList.route) {
            PodcastListScreen()
        }
        composable(route = Screen.PodcastDetail.route) {
            PodcastDetailScreen()
        }

    }

}
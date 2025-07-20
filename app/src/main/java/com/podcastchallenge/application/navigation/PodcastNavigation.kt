package com.podcastchallenge.application.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.podcastchallenge.application.models.PodcastPresentation
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
        composable(route = Screen.PodcastList.route) {
            PodcastListScreen(
                onNavigateToDetails = { podcast->
                    navController.navigate(Screen.PodcastDetail.createRoute(podcast))
                }
            )
        }
        composable(
            route = Screen.PodcastDetail.route,
            arguments = listOf(navArgument(NavArgs.PODCAST_ARG) { type = NavType.StringType })
            ) { backStackEntry->
            val podcastJson = backStackEntry.arguments?.getString(NavArgs.PODCAST_ARG)
            val podcast = Gson().fromJson(podcastJson, PodcastPresentation::class.java)
            podcast?.let {
                PodcastDetailScreen(
                    podcast = podcast,
                    onNavigateBack = {
                        navController.popBackStack()
                    }
                )
            }
        }

    }

}
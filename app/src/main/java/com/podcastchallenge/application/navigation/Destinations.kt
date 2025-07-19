package com.podcastchallenge.application.navigation

sealed class Screen(val route: String) {
    data object PodcastList : Screen("podcast_list_screen")
    data object PodcastDetail : Screen("podcast_details_screen")
}
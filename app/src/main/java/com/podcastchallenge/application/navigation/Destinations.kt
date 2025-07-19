package com.podcastchallenge.application.navigation

sealed class Screen(val route: String) {
    data object PodcastsList : Screen("podcasts_list_screen")
    data object PodcastDetail : Screen("podcast_details_screen")
}
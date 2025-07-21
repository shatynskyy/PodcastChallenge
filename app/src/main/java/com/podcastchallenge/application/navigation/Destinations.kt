package com.podcastchallenge.application.navigation

import android.net.Uri
import com.google.gson.Gson
import com.podcastchallenge.application.models.PodcastPresentation
import com.podcastchallenge.application.navigation.NavArgs.PODCAST_ARG

/**
 * Defines the navigation destinations (screens) in the app.
 *
 * Each screen has a unique [route] used in the navigation graph.
 */
sealed class Screen(val route: String) {
    data object PodcastList : Screen("podcast_list_screen")
    data object PodcastDetail : Screen("podcast_details_screen/{${PODCAST_ARG}}"){
        fun createRoute(podcast: PodcastPresentation): String {
            val encoded = Uri.encode(Gson().toJson(podcast))
            return "podcast_details_screen/$encoded"
        }
    }
}

object NavArgs {
    const val PODCAST_ARG = "podcast_args"
}
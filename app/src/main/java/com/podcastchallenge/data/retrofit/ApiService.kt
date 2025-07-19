package com.podcastchallenge.data.retrofit

import com.podcastchallenge.data.Endpoints.BEST_PODCASTS_URL
import com.podcastchallenge.data.models.BestPodcastsDTO
import retrofit2.http.GET

interface ApiService {

    @GET(BEST_PODCASTS_URL)
    fun getBestPodcasts() : BestPodcastsDTO

}
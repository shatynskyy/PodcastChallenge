package com.podcastchallenge.application

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

fun CoroutineScope.launchSafe(
    block: suspend () -> Unit
): Job =
    launch(CoroutineExceptionHandler { _, throwable -> throwable.printStackTrace() }) {
        block()
    }
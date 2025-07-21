package com.podcastchallenge.application

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Launches a coroutine with basic error handling (prints exceptions).
 */
fun CoroutineScope.launchSafe(
    block: suspend () -> Unit
): Job =
    launch(CoroutineExceptionHandler { _, throwable -> throwable.printStackTrace() }) {
        block()
    }
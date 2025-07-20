# 🎧 AUDIOBOOKS MOBILE CHALLENGE 

A modern Android application for browsing and favoriting podcasts built with the **MVI (Model-View-Intent)** pattern using Jetpack Compose.

## 🚀 Tech Stack

- **UI**: [Jetpack Compose](https://developer.android.com/jetpack/compose)
- **State Management**: **MVI architecture** with `StateFlow` and unidirectional data flow
- **Pagination**: [Paging 3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview)
- **Persistence**: [Room](https://developer.android.com/jetpack/androidx/releases/room)
- **Dependency Injection**: [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
- **Networking**: [Retrofit](https://square.github.io/retrofit/)
- **Testing**:
  - Unit tests with **JUnit** and **MockK**
  - Instrumentation tests using **Room Testing**

## ✨ Features

- Browse best podcasts using public API
- Add/remove podcasts to **favorites**
- Persist favorites locally using Room
- Reactive UI with StateFlow and Compose
- Paging support for seamless data loading
- Test coverage for Repository, and DAO layers

## 🧠 Architecture

PodcastChallenge uses the **MVI (Model-View-Intent)** pattern:

- **Model** - State data (via `StateFlow`, Room entities, Repository)
- **View** - Jetpack Compose UI that reacts to state
- **Intent** - Events triggered by user actions and dispatched to ViewModel

This results in a **unidirectional data flow**, making the app easier to debug, maintain, and test.

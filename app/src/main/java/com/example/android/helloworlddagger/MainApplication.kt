package com.example.android.helloworlddagger

import android.app.Application

// appComponent lives in the Application class to share its lifecycle
class MainApplication : Application() {
    // Reference to the application graph that is used across the whole app
    val appComponent: ApplicationComponent = DaggerApplicationComponent.create()
}

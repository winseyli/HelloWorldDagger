package com.example.android.helloworlddagger

import android.app.Application
import dagger.Component
import javax.inject.Inject
import javax.inject.Singleton

// scoping a type to the component's lifecycle
// https://developer.android.com/training/dependency-injection/dagger-android#dagger-scopes
@Singleton
// Definition of the Application graph
// The "modules" attribute in the @Component annotation tells Dagger what Modules
// to include when building the graph
@Component(modules = [FirstModule::class, SecondModule::class])
interface ApplicationComponent {
    // This tells Dagger that MainActivity requests injection so the graph needs to
    // satisfy all the dependencies of the fields that MainActivity is requesting.
    fun inject(activity: MainActivity)
}

// appComponent lives in the Application class to share its lifecycle
class MainApplication : Application() {
    // Reference to the application graph that is used across the whole app
    val appComponent: ApplicationComponent = DaggerApplicationComponent.create()
}

package com.example.android.helloworlddagger

import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

// scoping a type to the component's lifecycle
// https://developer.android.com/training/dependency-injection/dagger-android#dagger-scopes
@Singleton
// Definition of the Application graph
// The "modules" attribute in the @Component annotation tells Dagger what Modules
// to include when building the graph
@Component(modules = [FirstModule::class, SecondModule::class, FirstSubcomponentModule::class])
interface ApplicationComponent {
    // This tells Dagger that MainActivity requests injection so the graph needs to
    // satisfy all the dependencies of the fields that MainActivity is requesting.
    fun inject(activity: MainActivity)

    // This function exposes the FirstSubcomponent Factory out of the graph so consumers
    // can use it to obtain new instances of FirstSubcomponent
    fun firstSubcomponent(): FirstSubcomponent.Factory
}

// @Inject tells Dagger how to create instances of MainViewModel
class MainViewModel @Inject constructor() {
    val text = "Hello World Dagger 2"
}

//===

// @Inject tells Dagger how to create instances of Info
class Info @Inject constructor() {
    val text = "Love Dagger 2 with @Provides and @Module"
}

// class DataSource @Inject constructor(val text: String), make it simpler...
class DataSource(val info: Info)

// @Module informs Dagger that this class is a Dagger Module
@Module
class FirstModule {
    // Way to scope types inside a Dagger Module
    @Singleton
    // @Provides tell Dagger how to create instances of the type that this function
    // returns (i.e. DataSource).
    // Function parameters are the dependencies of this type.
    @Provides
    open fun providesDataSource(
        info: Info
    ): DataSource {
        // Whenever Dagger needs to provide an instance of type DataSource,
        // this code (the one inside the @Provides method) is run.
        return DataSource(info)
    }
}

//===

class Message(val text: String)

@Module
class SecondModule {
    @Provides
    @Named("MessageA")
    fun provideMessage123(): Message {
        return Message("Message 123")
    }

    @Provides
    @Named("MessageB")
    fun provideMessage456(): Message {
        return Message("Message 456")
    }
}

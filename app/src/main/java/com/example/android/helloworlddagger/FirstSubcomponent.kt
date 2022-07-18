package com.example.android.helloworlddagger

import dagger.Module
import dagger.Subcomponent
import javax.inject.Inject
import javax.inject.Scope

// Definition of a custom scope called ActivityScope
@Scope
@Retention(value = AnnotationRetention.RUNTIME)
annotation class ActivityScope

// Classes annotated with @ActivityScope are scoped to the graph and the same
// instance of that type is provided every time the type is requested.
@ActivityScope
// @Subcomponent annotation informs Dagger this interface is a Dagger Subcomponent
@Subcomponent
interface FirstSubcomponent {
    // MUST define a subcomponent factory so that ApplicationComponent knows how to create instances of FirstSubcomponent
    // Factory that is used to create instances of this subcomponent
    @Subcomponent.Factory
    interface Factory {
        fun create(): FirstSubcomponent
    }

    // This tells Dagger that these Activities/Fragments request injection from FirstSubcomponent
    // so that this subcomponent graph needs to satisfy all the dependencies of the
    // fields that these Activities/Fragments are injecting
    fun inject(activity: SecondActivity)
    fun inject(fragment: FirstFragment)
    fun inject(fragment: SecondFragment)
}

// The "subcomponents" attribute in the @Module annotation tells Dagger what
// Subcomponents are children of the Component this module is included in.
@Module(subcomponents = [FirstSubcomponent::class])
class FirstSubcomponentModule {}

// A unique instance of FirstViewModel is provided in Components
// annotated with @ActivityScope
@ActivityScope
class FirstViewModel @Inject constructor() {
    val text = "Hello World Dagger subcomponents, random number ${(0..10).random()}"
}

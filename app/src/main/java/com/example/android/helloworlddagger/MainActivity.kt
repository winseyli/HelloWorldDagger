package com.example.android.helloworlddagger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.helloworlddagger.databinding.ActivityMainBinding
import dagger.Module
import dagger.Provides
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    // View Binding
    private lateinit var binding: ActivityMainBinding

    // You want Dagger to provide an instance of MainViewModel from the graph
    @Inject lateinit var viewModel: MainViewModel
    @Inject lateinit var dataSource: DataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        // Make Dagger instantiate @Inject fields in MainActivity
        (applicationContext as MainApplication).appComponent.inject(this)
        // Now MainViewModel is available

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            textViewFromInject.text = viewModel.text
            textViewFromModuleProvides.text = dataSource.text
        }
        val view = binding.root
        setContentView(view)
    }
}

// @Inject tells Dagger how to create instances of MainViewModel
class MainViewModel @Inject constructor() {
    val text = "Hello World Dagger 2"
}

// class DataSource @Inject constructor(val text: String), make it simpler...
class DataSource(val text: String)

// @Module informs Dagger that this class is a Dagger Module
@Module
class FirstModule {

    // @Provides tell Dagger how to create instances of the type that this function
    // returns (i.e. DataSource).
    // Function parameters are the dependencies of this type.
    @Provides
    open fun providesDataSource(): DataSource {
        // Whenever Dagger needs to provide an instance of type DataSource,
        // this code (the one inside the @Provides method) is run.
        return DataSource("Love Dagger 2 with @Provides and @Module")
    }
}

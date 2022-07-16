package com.example.android.helloworlddagger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.helloworlddagger.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    // View Binding
    private lateinit var binding: ActivityMainBinding

    // You want Dagger to provide an instance of MainViewModel from the graph
    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        // Make Dagger instantiate @Inject fields in MainActivity
        (applicationContext as MainApplication).appComponent.inject(this)
        // Now MainViewModel is available

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            textView.text = viewModel.text
        }
        val view = binding.root
        setContentView(view)
    }
}

// @Inject tells Dagger how to create instances of MainViewModel
class MainViewModel @Inject constructor() {
    val text = "Hello World Dagger 2"
}

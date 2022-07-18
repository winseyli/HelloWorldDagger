package com.example.android.helloworlddagger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.helloworlddagger.databinding.ActivitySecondBinding
import javax.inject.Inject

class SecondActivity : AppCompatActivity() {

    // View Binding
    private lateinit var binding: ActivitySecondBinding

    // Reference to the Login graph
    lateinit var firstSubcomponent: FirstSubcomponent

    // Fields that need to be injected by the login graph
    @Inject
    lateinit var viewModel: FirstViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        // Creation of the login graph using the application graph
        firstSubcomponent = (applicationContext as MainApplication)
            .appComponent.firstSubcomponent().create()

        // Make Dagger instantiate @Inject fields in LoginActivity
        firstSubcomponent.inject(this)

        // Now loginViewModel is available

        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater).apply {
            textViewFromInject.text = viewModel.text
        }
        val view = binding.root
        setContentView(view)
    }
}
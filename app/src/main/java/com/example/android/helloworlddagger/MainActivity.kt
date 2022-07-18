package com.example.android.helloworlddagger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.helloworlddagger.databinding.ActivityMainBinding
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

class MainActivity : AppCompatActivity() {

    // View Binding
    private lateinit var binding: ActivityMainBinding

    // You want Dagger to provide an instance of MainViewModel from the graph
    @Inject lateinit var viewModel: MainViewModel
    @Inject lateinit var dataSource: DataSource
    @Inject @field:Named("MessageA") lateinit var message123: Message
    @Inject @field:Named("MessageB") lateinit var message456: Message

    override fun onCreate(savedInstanceState: Bundle?) {
        // Make Dagger instantiate @Inject fields in MainActivity
        (applicationContext as MainApplication).appComponent.inject(this)
        // Now MainViewModel is available

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            textViewFromInject.text = viewModel.text
            textViewFromModuleProvides.text = dataSource.info.text
            textViewFromMessageA.text = message123.text
            textViewFromMessageB.text = message456.text
        }
        val view = binding.root
        setContentView(view)
    }
}

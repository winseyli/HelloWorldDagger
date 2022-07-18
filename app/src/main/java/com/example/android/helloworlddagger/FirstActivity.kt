package com.example.android.helloworlddagger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.helloworlddagger.databinding.ActivityFirstBinding

class FirstActivity : AppCompatActivity() {

    // View Binding
    private lateinit var binding: ActivityFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}

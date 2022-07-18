package com.example.android.helloworlddagger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.helloworlddagger.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    // View Binding
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}
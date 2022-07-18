package com.example.android.helloworlddagger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class FirstActivity : AppCompatActivity() {

    // Reference to the Login graph
    lateinit var firstSubcomponent: FirstSubcomponent

    override fun onCreate(savedInstanceState: Bundle?) {
        // Creation of the login graph using the application graph
        firstSubcomponent = (applicationContext as MainApplication)
            .appComponent.firstSubcomponent().create()

        // Now loginViewModel is available

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
    }
}

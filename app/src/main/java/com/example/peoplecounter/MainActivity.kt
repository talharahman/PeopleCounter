package com.example.peoplecounter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.peoplecounter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil
            .setContentView(this, R.layout.activity_main)

        val factory = CounterViewModelFactory(application)
        val viewModel = ViewModelProvider(this, factory)
            .get(CounterViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }
}


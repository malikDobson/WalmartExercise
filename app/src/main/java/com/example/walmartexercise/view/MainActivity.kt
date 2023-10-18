package com.example.walmartexercise.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.walmartexercise.R
import com.example.walmartexercise.databinding.ActivityMainBinding
import com.example.walmartexercise.view.adapter.CountryAdapter
import com.example.walmartexercise.viewmodel.CountryViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_activity_main)
        if (fragment == null){
            supportFragmentManager.commit {
                add(R.id.fragment_activity_main, DisplayCountryFragment())
            }
        }
    }
}
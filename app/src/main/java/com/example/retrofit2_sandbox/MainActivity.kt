package com.example.retrofit2_sandbox

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.retrofit2_sandbox.data.MainViewState

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button).setOnClickListener {
            viewModel.fetchData()
        }
        viewModel.viewState.observe(this) {
            viewStateHandler(it)
        }
    }

    private fun viewStateHandler(viewState: MainViewState) {
        when (viewState) {
            is MainViewState.Success -> {
                findViewById<TextView>(R.id.text_field).text = viewState.list.toString()
            }
            is MainViewState.Error -> {
                findViewById<TextView>(R.id.text_field).text = viewState.message
            }
        }
    }

}
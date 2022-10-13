package com.example.retrofit2_sandbox

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit2_sandbox.data.MainViewState

class MainActivity : AppCompatActivity() {
    private val adapter = MarsPropertyAdapter()

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

        findViewById<RecyclerView>(R.id.recyclerView).adapter = adapter
    }

    private fun viewStateHandler(viewState: MainViewState) {
        when (viewState) {
            is MainViewState.Success -> {
                adapter.data = viewState.list
                adapter.notifyDataSetChanged()
            }
            is MainViewState.Error -> {
                println(viewState.message)
            }
        }
    }

}
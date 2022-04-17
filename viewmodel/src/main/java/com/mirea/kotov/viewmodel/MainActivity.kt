package com.mirea.kotov.viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private var progressBar: ProgressBar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar = findViewById(R.id.progressBar)

        val viewModel: ProgressViewModel =
            ViewModelProvider(this).get(ProgressViewModel::class.java)

        viewModel.getProgressState().observe(this
        ) { isVisibleProgressBar ->
            run {
                if (isVisibleProgressBar) {
                    progressBar!!.visibility = View.VISIBLE
                } else {
                    progressBar!!.visibility = View.GONE
                }
            }
        }
        viewModel.showProgress()
    }
}
package com.mirea.kotov.networkstate

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {
    private var textView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)

        val networkLiveData: LiveData<String>? = NetworkLiveData.getInstance(this)
        networkLiveData!!.observe(
            this
        ) { value -> textView!!.text = value }
    }
}
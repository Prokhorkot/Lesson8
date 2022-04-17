package com.mirea.kotov.lifedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.Observer
import android.os.Handler
import android.os.Looper
import android.util.Log

class MainActivity : AppCompatActivity(), Observer<String?> {
    private var networkNameTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        networkNameTextView = findViewById(R.id.textView)

        TimeController.getDate().observe(this, this)
        Handler(Looper.getMainLooper()).postDelayed({
            TimeController.setTime()
        }, 5000)
    }

    override fun onChanged(t: String?) {
        Log.d(MainActivity::class.simpleName, t.toString())
        networkNameTextView!!.text = t.toString()
    }
}
package com.example.socialmediareference

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MoreDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_details)

        val title = intent.getStringExtra("title")
        val description = intent.getStringExtra("description")
        Log.d("MoreDetailsActivity", "Title: $title, Description: $description")

        val historyTextView = findViewById<TextView>(R.id.historyTextView)
        historyTextView.text = description

        val backButton = findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener {
            val resultIntent = intent
            resultIntent.putExtra("title", title)
            resultIntent.putExtra("description", description)
            setResult(RESULT_OK, resultIntent)
            finish()
        }

        val cancelButton = findViewById<Button>(R.id.cancelButton)
        cancelButton.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }

    }
}
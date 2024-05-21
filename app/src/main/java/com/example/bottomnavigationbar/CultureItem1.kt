package com.example.bottomnavigationbar

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CultureItem1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_culture_item1)

        val imageView = findViewById<ImageView>(R.id.iv_backspace)
        imageView.setOnClickListener {
            finish()
        }

    }
}
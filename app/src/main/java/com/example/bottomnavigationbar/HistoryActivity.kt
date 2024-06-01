package com.example.bottomnavigationbar

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bottomnavigationbar.databinding.ActivityHistoryBinding
import com.example.bottomnavigationbar.databinding.ActivityInteractiveTestBinding

class HistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.zovstepei.setOnClickListener {
            val intent = Intent(this@HistoryActivity, History1Activity::class.java)
            startActivity(intent)
        }

        val imageView = findViewById<ImageView>(R.id.iv_backspace_history)
        imageView.setOnClickListener {
            finish()
        }
    }
}
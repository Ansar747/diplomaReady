package com.example.bottomnavigationbar

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bottomnavigationbar.databinding.ActivityInteractiveTestBinding

class InteractiveTestActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInteractiveTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInteractiveTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.testHistoryOfKz.setOnClickListener {
            val intent = Intent(this@InteractiveTestActivity, QuestionMainTest::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}

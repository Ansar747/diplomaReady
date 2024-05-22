package com.example.bottomnavigationbar

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TestResultsActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_results)

        val results = intent.getBooleanArrayExtra("results")

        val resultTextView = findViewById<TextView>(R.id.tvScore)
        if (results != null) {
            resultTextView.text = "Результат тестирования: ${results.count { it }} / ${results.size}"
        }

        val imageButton = findViewById<Button>(R.id.btnReturnToInteractiveActivity)
        imageButton.setOnClickListener {
            navigateToInteractiveActivity()
        }

    }

    private fun navigateToInteractiveActivity() {
        val intent = Intent(this, InteractiveTestActivity::class.java)
        startActivity(intent)
    }

}
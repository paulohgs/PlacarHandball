package com.example.placarhandball

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class ConfigActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)
    }

    fun startGame(v: View) {
        val intent = Intent(this, PlacarActivity::class.java).apply {
            putExtra("time", "5")
        }
        startActivity(intent)
    }
}
package com.example.placarhandball

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startGameConfiguration(v: View) {
        val intent = Intent(this, ConfigActivity::class.java).apply {
            putExtra("bd", "teste")
        }
        startActivity(intent)
    }
}
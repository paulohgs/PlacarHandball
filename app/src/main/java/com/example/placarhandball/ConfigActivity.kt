package com.example.placarhandball

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.placarhandball.model.Placar

class ConfigActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)

        val teamA: EditText = findViewById(R.id.equipeA)
        val teamB: EditText = findViewById(R.id.equipeB)
        val time: EditText = findViewById(R.id.timeGame)

        val startGameButton: Button = findViewById(R.id.startGame)
        startGameButton.setOnClickListener {
            val timeLong = time.text.toString().toLong()
            val placarModel = Placar(teamA.text.toString(), teamB.text.toString(), timeLong)
            Log.d("PDM23", timeLong.toString())
            val intent = Intent(this, PlacarActivity::class.java).apply {
                putExtra("placarModel", placarModel)
            }
            startActivity(intent)
        }
    }
}
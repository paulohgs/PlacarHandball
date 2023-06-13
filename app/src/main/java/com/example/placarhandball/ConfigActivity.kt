package com.example.placarhandball

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.placarhandball.model.HandballGame

@Suppress("DEPRECATION")
class ConfigActivity : AppCompatActivity() {
    lateinit var stack: ArrayList<HandballGame>

    var placarModel = HandballGame(
        "Equipe A",
        "Equipe B",
        0,
        0,
        30
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)

        stack = intent.getSerializableExtra("stack") as ArrayList<HandballGame>

        openConfig()

        val startGameButton: Button = findViewById(R.id.startGame)
        startGameButton.setOnClickListener {
            updatePlacar()
            saveConfig()
            val intent = Intent(this, PlacarActivity::class.java).apply {
                putExtra("placarModel", placarModel).putExtra("stack", stack)
            }
            startActivity(intent)
        }
    }

    fun updatePlacar() {
        val teamA: EditText = findViewById(R.id.equipeA)
        val teamB: EditText = findViewById(R.id.equipeB)
        val time: EditText = findViewById(R.id.timeGame)

        placarModel.teamA = teamA.text.toString()
        placarModel.teamB = teamB.text.toString()
        placarModel.time = time.text.toString().toLong()
    }

    fun saveConfig() {
        val configFilename = "config_placar"
        val sp = getSharedPreferences(configFilename, Context.MODE_PRIVATE)
        val edShared = sp.edit()
        edShared.putString("teamA", placarModel.teamA)
        edShared.putString("teamB", placarModel.teamB)
        edShared.putLong("timeLong", placarModel.time)
        edShared.apply()
    }

    fun openConfig() {
        val filename = "config_placar"
        val sp = getSharedPreferences(filename, Context.MODE_PRIVATE)
        placarModel.teamA = sp.getString("teamA", "Equipe A").toString()
        placarModel.teamB = sp.getString("teamB", "Equipe B").toString()
        placarModel.time = sp.getLong("timeLong", 30)
    }
}
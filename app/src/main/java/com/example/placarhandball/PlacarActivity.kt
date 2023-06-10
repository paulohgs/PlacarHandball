package com.example.placarhandball

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.TextView
import com.example.placarhandball.model.HandballGame
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.ObjectOutputStream
import java.nio.charset.StandardCharsets

@Suppress("DEPRECATION")
class PlacarActivity : AppCompatActivity() {

    lateinit var stack: ArrayList<HandballGame>
    lateinit var placarConfig: HandballGame
    var START_MILLI_SECONDS = 60000L
    lateinit var countDownTimer: CountDownTimer
    var isRunning: Boolean = false
    var time_in_milli_seconds = 0L
    lateinit var timerTextView: TextView
    var time: Long = 0
    var pontuationA = 0
    var pontutaionB = 0
    var oldPontuationA = 0
    var oldPontuationB = 0
    var altA = false
    var altB = false
    lateinit var placar: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_placar)

        placarConfig = intent.getSerializableExtra("placarModel") as HandballGame
        stack = intent.getSerializableExtra("stack") as ArrayList<HandballGame>
        time = placarConfig.time
        time_in_milli_seconds = time * 60000L
        Log.d("PDM23", time.toString())

        timerTextView = findViewById(R.id.timerTextView)
        timerTextView.setOnClickListener {
            if(isRunning) {
                pauseTimer()
            } else {
                startTimer()
            }
        }
        placar = findViewById(R.id.placar)

        val teamA: TextView = findViewById(R.id.teamA)
        teamA.setText(placarConfig.teamA.toString())
        teamA.setOnClickListener {
            oldPontuationA = pontuationA
            pontuationA += 1
            placar.setText("$pontuationA x $pontutaionB")
            altA = true
            altB = false
        }

        val teamB: TextView = findViewById(R.id.teamB)
        teamB.setText(placarConfig.teamB.toString())
        teamB.setOnClickListener {
            oldPontuationB = pontutaionB
            pontutaionB += 1
            placar.setText("$pontuationA x $pontutaionB")
            altB = true
            altA = false
        }
    }

    fun revertPontuation(v: View) {
        var aux = 0
        if(altA) {
            aux = pontuationA
            pontuationA = oldPontuationA
            oldPontuationA = aux
        } else if(altB) {
            aux = pontutaionB
            pontutaionB = oldPontuationB
            oldPontuationB = aux
        }
        placar.setText("$pontuationA x $pontutaionB")
    }

    fun pauseTimer() {
        countDownTimer.cancel()
        isRunning = false
    }

    fun startTimer() {
        countDownTimer = object : CountDownTimer(time_in_milli_seconds, 1000) {
            override fun onFinish() {
                Log.d("PDM23", "FINALIZOU")
            }
            override fun onTick(p0: Long) {
                time_in_milli_seconds = p0
                updateTextUI()
            }
        }
        countDownTimer.start()
        isRunning = true
    }

    fun updateTextUI() {
        val minute = (time_in_milli_seconds/1000)/60
        val seconds = (time_in_milli_seconds/1000)%60
        timerTextView.setText("$minute:$seconds")
    }

    fun saveGame() {
        val filename = "previousGame"
        val sp = getSharedPreferences(filename, Context.MODE_PRIVATE)
        var edShared = sp.edit()

        var count = stack.size

        edShared.putInt("numberOfMatches", count)
        edShared.putString("teamA", placarConfig.teamA)
        edShared.putString("teamB", placarConfig.teamB)
        edShared.putLong("time", placarConfig.time)

        var data = ByteArrayOutputStream()
        var oos = ObjectOutputStream(data)
        oos.writeObject(placarConfig)

        edShared.putString("match ${count}", data.toString(StandardCharsets.ISO_8859_1.name()))
        edShared.commit()

        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("ended", true)
        }
        startActivity(intent)
    }
}
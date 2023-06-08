package com.example.placarhandball

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.placarhandball.model.Placar
import org.w3c.dom.Text

@Suppress("DEPRECATION")
class PlacarActivity : AppCompatActivity() {

    var START_MILLI_SECONDS = 60000L

    lateinit var countDownTimer: CountDownTimer
    var isRunning: Boolean = false
    var time_in_milli_seconds = 0L
    lateinit var timerTextView: TextView
    var time: Long = 0
    var pontuationA: Int = 0
    var pontutaionB: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_placar)

        val placarConfig = intent.getSerializableExtra("placarModel") as Placar
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
        val placar: TextView = findViewById(R.id.placar)
        val teamA: TextView = findViewById(R.id.teamA)
        teamA.setText(placarConfig.teamA.toString())
        teamA.setOnClickListener {
            pontuationA += 1
            placar.setText("$pontuationA x $pontutaionB")
        }
        val teamB: TextView = findViewById(R.id.teamB)
        teamB.setText(placarConfig.teamB.toString())
        teamB.setOnClickListener {
            pontutaionB += 1
            placar.setText("$pontuationA x $pontutaionB")
        }
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
}
package com.example.placarhandball

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView

class PlacarActivity : AppCompatActivity() {

    var START_MILLI_SECONDS = 60000L

    lateinit var countDownTimer: CountDownTimer
    var isRunning: Boolean = false
    var time_in_milli_seconds = 0L
    lateinit var timerTextView: TextView
    var time: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_placar)

        time = intent.getStringExtra("time")?.toLong() ?: 30
        Log.d("PDM23", time.toString())

        timerTextView = findViewById(R.id.timerTextView)
        val button: Button = findViewById(R.id.button)
        timerTextView.setOnClickListener {
            if(isRunning) {
                pauseTimer()
            } else {
                time_in_milli_seconds = time * 60000L
                startTimer(time_in_milli_seconds)
            }
        }
    }

    fun pauseTimer() {
        countDownTimer.cancel()
        isRunning = false
    }

    fun startTimer(time_in_seconds: Long) {
        countDownTimer = object : CountDownTimer(time_in_seconds, 1000) {
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
package com.example.placarhandball

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.placarhandball.model.HandballGame
import com.google.android.material.snackbar.Snackbar
import java.io.ByteArrayInputStream
import java.io.ObjectInputStream

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ended = intent.getBooleanExtra("ended", false)

        if(ended) {
            showMessage(findViewById(R.id.main_screen))
        }
    }

    fun startGameConfiguration(v: View) {
        var stack = openStack()
        val intent = Intent(this, ConfigActivity::class.java).apply {
            putExtra("stack", stack)
        }
        startActivity(intent)
    }

    fun openStack(): ArrayList<HandballGame> {
        val filename = "previousGame"
        val sp = getSharedPreferences(filename, Context.MODE_PRIVATE)

        val data = ArrayList<HandballGame>()
        var count = 0
        for(i in sp.all) {
            var stringObj = sp.getString("match ${count}", "").toString()

            if(stringObj.length >= 1) {
                var dis = ByteArrayInputStream(stringObj.toByteArray(Charsets.ISO_8859_1))
                var oos = ObjectInputStream(dis)
                var placar = oos.readObject() as HandballGame
                data.add(placar)
            }
            count += 1
        }
        return data
    }

    fun gameHistoric(v: View) {
        var stack = openStack()
        val intent = Intent(this, HistActivity::class.java).apply {
            putExtra("stack", stack)
        }
    }

    fun showMessage(v: View) {
        val duration = Snackbar.LENGTH_LONG
        val text = "O jogo foi finalizado."
        val snack = Snackbar.make(v,text,duration)
        snack.show()
    }
}
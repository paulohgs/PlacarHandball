package com.example.placarhandball

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.placarhandball.adapter.CustomAdapter
import com.example.placarhandball.model.HandballGame

@Suppress("DEPRECATION")
class HistActivity : AppCompatActivity() {
    lateinit var stack: ArrayList<HandballGame>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hist)

        stack = intent.getSerializableExtra("stack") as ArrayList<HandballGame>

        val recyclerView = findViewById<RecyclerView>(R.id.rc_histGames)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = CustomAdapter(stack.reversed().slice(0..4))

        recyclerView.adapter = adapter
    }
}
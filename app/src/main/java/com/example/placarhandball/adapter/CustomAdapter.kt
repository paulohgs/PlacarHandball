package com.example.placarhandball.adapter

import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.placarhandball.R
import com.example.placarhandball.model.HandballGame
import com.google.android.material.snackbar.Snackbar

class CustomAdapter(private val mList: List<HandballGame>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {


    // Criação de Novos ViewHolders
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // infla o card_previous_games
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_previous_game, parent, false)

        return ViewHolder(view)
    }

    // Ligando o Recycler view a um View Holder
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = ItemView.findViewById(R.id.imageview)
        val tvNomePartida: TextView = ItemView.findViewById(R.id.idNomePartida)
        val tvResultadoJogo: TextView = ItemView.findViewById(R.id.tvResultadoJogo)
        val lnCell: LinearLayout = ItemView.findViewById(R.id.lnCell)
    }

    // faz o bind de uma ViewHolder a um Objeto da Lista
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val placarAnterior = mList[position]

        //alimentando os elementos a partir do objeto placar
        holder.tvNomePartida.text = "${placarAnterior.teamA} X ${placarAnterior.teamB}"
        holder.tvResultadoJogo.text = "${placarAnterior.pontuationA} X ${placarAnterior.pontuationB}"

        holder.lnCell.setOnClickListener{
            val duration= Snackbar.LENGTH_LONG
            val text = "${placarAnterior.teamA} ${placarAnterior.pontuationA} X ${placarAnterior.pontuationB} ${placarAnterior.teamB}"

            val snack= Snackbar.make(holder.lnCell,text,duration)
            snack.show()

        }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }
}
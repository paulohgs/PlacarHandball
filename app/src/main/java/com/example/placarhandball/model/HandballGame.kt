package com.example.placarhandball.model

import java.io.Serializable

data class HandballGame(
    val teamA: String,
    val teamB: String,
    val pontuationA: Int,
    val pontuationB: Int,
    val id_partida: String,
    val hasTimer: Boolean): Serializable

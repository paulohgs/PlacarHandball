package com.example.placarhandball.model

import java.io.Serializable

data class HandballGame(
    var teamA: String,
    var teamB: String,
    var pontuationA: Long,
    var pontuationB: Long,
    var time: Long): Serializable

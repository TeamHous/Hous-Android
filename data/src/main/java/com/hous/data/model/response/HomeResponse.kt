package com.hous.data.model.response

import com.hous.data.entity.Event
import com.hous.data.entity.Homie
import com.hous.data.entity.Rule

data class HomeResponse(
    val eventList: List<Event>,
    val keyRulesList: List<String>,
    val todoList: List<Rule>,
    val homieProfileList: List<Homie>,
    val roomCode: String
)

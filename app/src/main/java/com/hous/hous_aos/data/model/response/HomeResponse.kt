package com.hous.hous_aos.data.model.response

import com.hous.hous_aos.data.entity.Event
import com.hous.hous_aos.data.entity.Homie
import com.hous.hous_aos.data.entity.Rule

data class HomeResponse(
    val eventList: List<Event>,
    val keyRulesList: List<String>,
    val todoList: List<Rule>,
    val homieProfileList: List<Homie>,
    val roomCode: String
)
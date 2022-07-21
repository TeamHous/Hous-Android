package com.hous.hous_aos.data.model.request

data class EventListRequest(
    val eventName: String,
    val eventIcon: String,
    val date: String,
    val participants: List<String>
)

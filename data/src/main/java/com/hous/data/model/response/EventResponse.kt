package com.hous.data.model.response

import com.google.gson.annotations.SerializedName
import com.hous.hous_aos.data.entity.Homie

data class EventResponse(
    @SerializedName("_id") val id: String,
    val eventName: String,
    val eventIcon: String,
    val date: String,
    val participants: List<Homie>
)

package com.hous.hous_aos.data.entity.rules

import com.google.gson.annotations.SerializedName

/**
 * EventData에서 paricipants랑 지네릭 T이 달라서 따로 빼야할 듯??*/
data class RequestEventData(
    @SerializedName("_id")
    val id: String,
    val eventName: String,
    val eventIcon: String,
    val date: String,
    val participants: List<String>
)

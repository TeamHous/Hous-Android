package com.hous.hous_aos.data.entity

import com.google.gson.annotations.SerializedName

/* date 는 서버에서 Date 타입으로 받음 여기선 String으로 줘도 되나? */
data class Event(
    @SerializedName("_id") val id: String = "",
    val eventName: String = "",
    val eventIcon: String = "",
    val dDay: String = "",
    var date: String = "",
    val participants: List<Homie> = emptyList()
)

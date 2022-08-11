package com.hous.data.model.response

import com.google.gson.annotations.SerializedName
import com.hous.data.entity.Homie

data class TempManagerResponse(
    @SerializedName("_id")
    val id: String = "",
    val homies: List<Homie> = emptyList()
)
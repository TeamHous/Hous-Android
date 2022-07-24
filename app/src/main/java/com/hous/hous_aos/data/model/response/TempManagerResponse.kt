package com.hous.hous_aos.data.model.response

import com.google.gson.annotations.SerializedName
import com.hous.hous_aos.data.entity.Homie

data class TempManagerResponse(
    @SerializedName("_id")
    val id: String = "",
    val homies: List<Homie> = emptyList()
)

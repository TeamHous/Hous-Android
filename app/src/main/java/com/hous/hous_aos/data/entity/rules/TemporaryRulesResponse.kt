package com.hous.hous_aos.data.entity.rules

import com.google.gson.annotations.SerializedName
import com.hous.hous_aos.data.entity.Homie

data class TemporaryRulesResponse(
    @SerializedName("_id")
    val id: String,
    val homies: List<Homie>
)

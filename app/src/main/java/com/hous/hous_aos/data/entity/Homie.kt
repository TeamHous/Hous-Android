package com.hous.hous_aos.data.entity

import com.google.gson.annotations.SerializedName

data class Homie(
    @SerializedName("_id") val id: String? = null,
    val userName: String = "",
    val typeName: String = "",
    val typeColor: String = "",
    val job: String = "",
    val introduction: String = "",
    val hashTag: List<String> = emptyList(),
    val typeScore: List<Int> = emptyList(),
    var isChecked: Boolean = false,
    val notificationState: Boolean = false
)

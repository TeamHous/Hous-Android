package com.hous.hous_aos.data.model.response

import com.google.gson.annotations.SerializedName

data class TypeTestListResponse(
    @SerializedName("_id") val id: String,
    val testNum: Int,
    val question: String,
    val questionType: String,
    val answers: List<String>,
    val questionImg: String,
)

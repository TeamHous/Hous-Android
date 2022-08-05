package com.hous.data.entity

import com.google.gson.annotations.SerializedName

data class TypeTest(
    @SerializedName("_id") val id: String,
    val testNum: Int,
    val question: String,
    val questionType: String,
    val answers: List<String>,
    val questionImg: String,
    val type: TypeState = TypeState.NONE
)

enum class TypeState {
    NONE, ONE, TWO, THREE
}

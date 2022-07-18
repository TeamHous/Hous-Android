package com.hous.hous_aos.ui.profile

data class TypeTest(
    val _id: String,
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

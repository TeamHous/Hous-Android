package com.hous.hous_aos.data.model.response

data class TestResultResponse(
    val userName: String,
    val typeName: String,
    val typeColor: String,
    val typeImg: String,
    val typeOneComment: String,
    val typeDesc: String,
    val typeRulesTitle: String,
    val typeRules: List<String>,
    val good: Homie,
    val bad: Homie
) {
    data class Homie(
        val typeName: String,
        val typeImg: String
    )
}

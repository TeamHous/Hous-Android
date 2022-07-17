package com.hous.hous_aos.ui.profile

data class ResultData(
    val userName: String,
    val typeName: String,
    val typeColor: Int,
    val typeImg: String,
    val typeOneComment: String,
    val typeDesc: String,
    val shape: Int,
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

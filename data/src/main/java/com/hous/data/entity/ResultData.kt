package com.hous.data.entity

data class ResultData(
    val userName: String,
    val typeName: String,
    val typeColor: String,
    val typeImg: String,
    val typeOneComment: String,
    val typeDesc: String,
    val typeRulesTitle: String,
    val typeRules: List<String>,
    val good: HomieInfo,
    val bad: HomieInfo
) {
    data class HomieInfo(
        val typeName: String,
        val typeImg: String
    )
}

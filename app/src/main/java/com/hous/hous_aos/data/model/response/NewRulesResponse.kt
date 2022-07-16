package com.hous.hous_aos.data.model.response

data class NewRulesResponse(
    val ruleCategory: List<Category>,
    val homies: List<Homie>
) {
    data class Category(
        val _id: String,
        val categoryName: String
    )

    data class Homie(
        val _id: String,
        val name: String,
        val typeColor: String
    )
}

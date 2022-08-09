package com.hous.domain.model

data class NewRuleInfo(
    val ruleCategories: List<Category> = emptyList(),
    val homies: List<Homie> = emptyList()
)

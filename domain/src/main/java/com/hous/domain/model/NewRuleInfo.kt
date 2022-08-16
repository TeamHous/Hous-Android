package com.hous.domain.model

data class NewRuleInfo(
    val ruleCategories: List<CategoryInfo> = emptyList(),
    val homies: List<HomieInfo> = emptyList()
)

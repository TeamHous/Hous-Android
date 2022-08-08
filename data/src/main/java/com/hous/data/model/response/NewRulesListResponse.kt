package com.hous.data.model.response

import com.hous.data.entity.Category
import com.hous.data.entity.Homie

data class NewRulesListResponse(
    val ruleCategories: List<Category> = emptyList(),
    val homies: List<Homie> = emptyList()
)

package com.hous.hous_aos.data.model.response

import com.hous.hous_aos.data.entity.Category
import com.hous.hous_aos.data.entity.Homie

data class NewRulesListResponse(
    val ruleCategories: List<Category> = emptyList(),
    val homies: List<Homie> = emptyList()
)

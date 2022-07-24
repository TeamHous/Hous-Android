package com.hous.hous_aos.data.model.response

import com.hous.hous_aos.data.entity.Category
import com.hous.hous_aos.data.entity.Rule

data class RulesTodayInfoListResponse(
    val homeRuleCategories: List<Category> = emptyList(),
    val todayTodoRules: List<Rule> = emptyList()
)
package com.hous.data.model.response

import com.hous.data.entity.Category
import com.hous.data.entity.Rule

data class RulesTodayInfoListResponse(
    val homeRuleCategories: List<Category> = emptyList(),
    val todayTodoRules: List<Rule> = emptyList()
)

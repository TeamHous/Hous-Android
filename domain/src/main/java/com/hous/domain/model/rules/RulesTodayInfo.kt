package com.hous.domain.model.rules

import com.hous.domain.model.CategoryInfo
import com.hous.domain.model.RuleInfo

data class RulesTodayInfo(
    val homeRuleCategories: List<CategoryInfo> = emptyList(),
    val todayTodoRules: List<RuleInfo> = emptyList()
)

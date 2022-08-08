package com.hous.data.model.response

import com.hous.data.entity.Rule

data class RulesTableResponse(
    val keyRules: List<Rule>,
    val rules: List<Rule>
)

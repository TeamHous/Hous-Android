package com.hous.data.model.response

import com.hous.hous_aos.data.entity.Rule

data class RulesTableResponse(
    val keyRules: List<Rule>,
    val rules: List<Rule>
)

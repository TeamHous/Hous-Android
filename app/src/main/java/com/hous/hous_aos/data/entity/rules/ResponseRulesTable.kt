package com.hous.hous_aos.data.entity.rules

import com.hous.hous_aos.data.entity.Rule

data class ResponseRulesTable(
    val keyRulesResponse: List<Rule>,
    val generalRules: List<Rule>
)

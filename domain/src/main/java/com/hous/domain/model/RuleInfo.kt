package com.hous.domain.model

data class RuleInfo(
    val id: String = "",
    var isChecked: Boolean = false,
    val ruleName: String = "",
    val todayMembersWithTypeColor: List<HomieInfo> = emptyList(),
    val isTmpMember: Boolean = false,
    val isAllChecked: Boolean = false,
    val membersCnt: Int = 0,
    val typeColors: List<String> = emptyList(),
    val categoryIcon: String = ""
)

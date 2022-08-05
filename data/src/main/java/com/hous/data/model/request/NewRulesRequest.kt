package com.hous.data.model.request

data class NewRulesRequest(
    val notificationState: Boolean = false,
    val ruleName: String = "",
    val categoryId: String = "",
    val isKeyRules: Boolean = false,
    val ruleMembers: List<Member> = emptyList()
)

data class Member(
    val userId: String? = null,
    val day: List<Int>
)

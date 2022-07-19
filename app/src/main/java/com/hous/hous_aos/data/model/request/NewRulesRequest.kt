package com.hous.hous_aos.data.model.request

data class NewRulesRequest(
    val notificationState: Boolean = false,
    val ruleName: String = "",
    val categoryId: String = "",
    val isKeyRules: Boolean = false,
    val ruleMember: List<Member> = emptyList(),
) {
    data class Member(
        val userId: String? = null,
        val day: List<Int> = emptyList()
    )
}

package com.hous.hous_aos.data.model.request

data class NewRulesRequest(
    val notificationState: Boolean,
    val ruleName: String,
    val categoryId: String,
    val isKeyRules: Boolean,
    val ruleMember: List<Member>,
) {
    data class Member(
        val userId: String,
        val day: List<Int>
    )
}

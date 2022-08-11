package com.hous.data.entity

import com.google.gson.annotations.SerializedName
import com.hous.domain.model.RuleInfo

data class Rule(
    @SerializedName("_id") val id: String = "",
    var isChecked: Boolean = false,
    val ruleName: String = "",
    val todayMembersWithTypeColor: List<Homie> = emptyList(),
    val isTmpMember: Boolean = false,
    val isAllChecked: Boolean = false,
    val membersCnt: Int = 0,
    val typeColors: List<String> = emptyList(),
    val categoryIcon: String = ""
) {
    fun toRuleInfo() = RuleInfo(
        id,
        isChecked,
        ruleName,
        todayMembersWithTypeColor.map { it.toHomieInfo() },
        isTmpMember,
        isAllChecked,
        membersCnt,
        typeColors,
        categoryIcon
    )
}

package com.hous.hous_aos.data.entity

import com.google.gson.annotations.SerializedName

data class Rule(
    @SerializedName("_id") val id: String = "",
    var isChecked: Boolean = false,
    val ruleName: String = "",
    val todayMembersWithTypeColor: List<Homie> = emptyList(),
    val isTmpMember: Boolean = false,
    val isAllChecked: Boolean = false,
    val membersCnt: Int = 0,
    val typeColors: List<String> = emptyList()
)

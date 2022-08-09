package com.hous.domain.model

data class Homie(
    val id: String? = null,
    val userName: String = "",
    val typeName: String = "",
    val typeId: String = "",
    val typeColor: String = "",
    val job: String = "",
    val introduction: String = "",
    val hashTag: List<String> = emptyList(),
    val typeScore: List<Int> = emptyList(),
    var isChecked: Boolean = false,
    val notificationState: Boolean = false
)

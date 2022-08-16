package com.hous.domain.model

data class CategoryInfo(
    val id: String = "",
    val categoryName: String = "",
    val categoryIcon: String = "",
    val ruleName: String = "",
    var isChecked: Boolean = false
)

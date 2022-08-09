package com.hous.domain.model

data class Category(
    val id: String = "",
    val categoryName: String = "",
    val categoryIcon: String = "",
    val ruleName: String = "",
    var isChecked: Boolean = false
)

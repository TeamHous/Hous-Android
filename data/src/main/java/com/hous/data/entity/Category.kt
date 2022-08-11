package com.hous.data.entity

import com.google.gson.annotations.SerializedName
import com.hous.domain.model.CategoryInfo

data class Category(
    @SerializedName("_id") val id: String = "",
    val categoryName: String = "",
    val categoryIcon: String = "",
    val ruleName: String = "",
    var isChecked: Boolean = false
) {
    fun toCategoryInfo() = CategoryInfo(id, categoryName, categoryIcon, ruleName, isChecked)
}

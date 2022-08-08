package com.hous.data.entity

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("_id") val id: String = "",
    val categoryName: String = "",
    val categoryIcon: String = "",
    val ruleName: String = "",
    var isChecked: Boolean = false
)

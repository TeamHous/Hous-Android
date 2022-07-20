package com.hous.hous_aos.data.entity

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("_id") val id: String = "",
    val categoryName: String = "",
    val categoryIcon: String = "",
    val ruleName: String = ""
)

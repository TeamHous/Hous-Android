package com.hous.hous_aos.data.entity.rules

import com.google.gson.annotations.SerializedName

data class MyToDoResponse(
    @SerializedName("_ids")
    val id: String,
    val categoryIcon: String,
    val ruleName: String,
    var isChecked: Boolean
)

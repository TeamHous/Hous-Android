package com.hous.hous_aos.data.entity.rules

import androidx.annotation.DrawableRes

data class CategoryOfRuleResponse(
    val name: String,
    @DrawableRes
    val backGround: Int, // 서버하고 연동할 때는 아마 String
    @DrawableRes
    val icon: Int // 서버하고 연동할 때는 아마 String
)

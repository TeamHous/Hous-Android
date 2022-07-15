package com.hous.hous_aos.data.entity.rules

import androidx.annotation.DrawableRes

data class CategoryOfRuleResponse(
    var isSelected: Boolean = false,
    val name: String,
    @DrawableRes
    val backGround: Int?,
    @DrawableRes
    val icon: Int // 서버하고 연동할 때는 아마 String
)
/**
 * val backGround: Int? 부분
 *  서버하고 연동할 때는 아마 String <- 얘는 아이콘에 따라 넣어주면 됨 내가 넣어주는 거*/

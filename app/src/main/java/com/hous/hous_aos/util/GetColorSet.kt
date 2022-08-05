package com.hous.hous_aos.util

import com.hous.hous_aos.R
import com.hous.data.entity.ColorSet

fun String.getColorSet(colorType: String): com.hous.data.entity.ColorSet {
    return when (colorType) {
        "PURPLE", "하이레벨 오돌이" -> com.hous.data.entity.ColorSet(
            colorBg = R.color.hous_purple_bg,
            colorBg2 = R.color.hous_purple_bg_2,
            colorPrimary = R.color.hous_purple
        )
        "GREEN", "룰 세터 육각이" -> com.hous.data.entity.ColorSet(
            colorBg = R.color.hous_green_bg,
            colorBg2 = R.color.hous_green_bg_2,
            colorPrimary = R.color.hous_green
        )
        "YELLOW", "늘 행복한 동글이" -> com.hous.data.entity.ColorSet(
            colorBg = R.color.hous_yellow_bg,
            colorBg2 = R.color.hous_yellow_bg_2,
            colorPrimary = R.color.hous_yellow
        )
        "BLUE", "룸메 맞춤형 네각이" -> com.hous.data.entity.ColorSet(
            colorBg = R.color.hous_blue_bg,
            colorBg2 = R.color.hous_blue_bg_2,
            colorPrimary = R.color.hous_blue
        )
        else -> com.hous.data.entity.ColorSet(
            colorBg = R.color.hous_red_bg,
            colorBg2 = R.color.hous_red_bg_2,
            colorPrimary = R.color.hous_red
        )
    }
}

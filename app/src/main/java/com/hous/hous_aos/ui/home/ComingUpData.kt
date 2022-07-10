package com.hous.hous_aos.ui.home

import androidx.annotation.DrawableRes

data class ComingUpData(
    @DrawableRes
    val shape: Int,
    @DrawableRes
    val image: Int,
    val day: String
)

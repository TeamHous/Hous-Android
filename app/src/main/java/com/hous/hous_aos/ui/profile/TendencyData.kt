package com.hous.hous_aos.ui.profile

data class TendencyData(
    val index: Int,
    val totalIndex:Int = 0,
    val title: String,
    val img: String,
    val answer: List<String>,
)
package com.hous.hous_aos.ui.profile

data class ProfileData(
    val userName: String,
    val job: String,
    val introduction: String,
    val hashTag: List<String>,
    val typeName: String,
    val typeColor: String,
    val typeScore: List<Int>,
    val notificationState: Boolean
)

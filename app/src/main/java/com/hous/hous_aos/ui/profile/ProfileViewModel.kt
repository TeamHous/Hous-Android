package com.hous.hous_aos.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {
    private val _profileData = MutableLiveData<ProfileData>(
        ProfileData(
            userName = "이영주",
            job = "대학생",
            introduction = "개발이 제일 쉬웠어요",
            hashTag = listOf(
                "ISFP",
                "이빵주",
                "안녕"
            ),
            typeName = "늘 행복한 동글이",
            typeColor = "PURPLE",
            typeScore = listOf(0, 1, 2, 3),
            notificationState = true
        )
    )
    val profileData get() = _profileData
}
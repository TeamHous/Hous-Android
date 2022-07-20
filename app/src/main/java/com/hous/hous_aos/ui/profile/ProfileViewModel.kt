package com.hous.hous_aos.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hous.hous_aos.data.entity.Homie

class ProfileViewModel : ViewModel() {
    private val _profileData = MutableLiveData<Homie>(
        Homie(
            userName = "이영주",
            job = "대학생",
            introduction = "개발이 제일 쉬웠어요 믿기지 않겠지만 사실입니다.",
            hashTag = listOf(
                "ISFP",
                "이빵주",
                "안녕"
            ),
            typeName = "늘 행복한 동글이",
            typeColor = "GRAY",
            typeScore = listOf(6, 6, 6, 6, 6),
            notificationState = true
        )
    )
    val profileData get() = _profileData
}

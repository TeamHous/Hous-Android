package com.hous.hous_aos.ui.profile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hous.hous_aos.data.entity.Homie
import com.hous.hous_aos.data.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {
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

    init {
        viewModelScope.launch {
            profileRepository.getUserProfile()
                .onSuccess {
                    Log.d("ProfileViewModel", "data : ${it.data}")
                    _profileData.value = it.data!!
                }
                .onFailure {
                    Log.d("ProfileViewModel", "data : ${it.message}")
                }
        }
    }
}

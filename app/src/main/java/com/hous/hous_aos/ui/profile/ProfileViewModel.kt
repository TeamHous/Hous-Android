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
    private val _profileData = MutableLiveData<Homie>()
    val profileData get() = _profileData

    init {
        getProfileDate()
    }

    fun getProfileDate() {
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

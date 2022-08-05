package com.hous.hous_aos.ui.profile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hous.data.entity.ResultData
import com.hous.data.repository.HomeRepository
import com.hous.data.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(
    private val profileRepository: com.hous.data.repository.ProfileRepository,
    private val homeRepository: com.hous.data.repository.HomeRepository
) : ViewModel() {
    private val _resultData = MutableLiveData<com.hous.data.entity.ResultData>()
    val resultData get() = _resultData

    private val _userId = MutableLiveData<String>()
    val userId get() = _userId

    fun myResult() {
        viewModelScope.launch {
            profileRepository.getMyResult()
                .onSuccess { result ->
                    Log.d("MyResultViewModel", "data : ${result.data}")
                    _resultData.value = result.data!!
                }
                .onFailure { result ->
                    Log.d("MyResultViewModel", "data : ${result.message}")
                }
        }
    }

    fun homieResult() {
        viewModelScope.launch {
            homeRepository.getHomieResult(userId= userId.value!!)
                .onSuccess { result ->
                    Log.d("HomieResultViewModel", "data : ${result.data}")
                    _resultData.value = result.data!!
                }
                .onFailure { result ->
                    Log.d("HomieResultViewModel", "data : ${result.message}")
                }
        }
    }
}
package com.hous.hous_aos.ui.profile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hous.data.entity.ResultData
import com.hous.data.repository.HomeRepository
import com.hous.data.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class ResultViewModel @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val homeRepository: HomeRepository
) : ViewModel() {
    private val _resultData = MutableLiveData<ResultData>()
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
            homeRepository.getHomieResult(userId = userId.value!!)
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

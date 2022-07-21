package com.hous.hous_aos.ui.profile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hous.hous_aos.data.entity.ResultData
import com.hous.hous_aos.data.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {
    private val _resultData = MutableLiveData<ResultData>()
    val resultData get() = _resultData

    init {
        viewModelScope.launch {
            profileRepository.getMyResult()
                .onSuccess { result ->
                    Log.d("ResultViewModel", "data : ${result.data}")
                    _resultData.value = result.data!!
                }
                .onFailure { result ->
                    Log.d("ResultViewModel", "data : ${result.message}")
                }
        }
    }
}
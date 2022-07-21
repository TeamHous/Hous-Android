package com.hous.hous_aos.ui.profile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hous.hous_aos.data.entity.ResultData
import com.hous.hous_aos.data.repository.ProfileRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class ResultViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {
    private val _resultData = MutableLiveData<ResultData>()
    val resultData get() = _resultData

    private val _typeId = MutableLiveData<String>()
    val typeId get() = _typeId

    fun getResult() {
        viewModelScope.launch {
            profileRepository.getMyResult(typeId = typeId.value!!)
                .onSuccess {
                    Log.d("ResultViewModel", "data : ${it.data}")
                    _resultData.value = it.data!!
                }
                .onFailure {
                    Log.d("ResultViewModel", "data : ${it.message}")
                }
        }
    }
}
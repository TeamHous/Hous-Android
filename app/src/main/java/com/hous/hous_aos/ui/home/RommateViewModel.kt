package com.hous.hous_aos.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hous.data.entity.Homie
import com.hous.data.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class RommateViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {
    private val _homieData = MutableLiveData(
        Homie(
            typeScore = listOf(3, 3, 3, 3, 3)
        )
    )
    val homieData get() = _homieData

    private val _homieId = MutableLiveData<String>()
    val homieId get() = _homieId

    fun getHomieList() {
        viewModelScope.launch {
            homeRepository.getHomieList(homieId = homieId.value!!)
                .onSuccess { result ->
                    Log.d("Homie", "data : ${result.data}")
                    _homieData.value = result.data!!
                }
                .onFailure { result ->
                    Log.d("Homie", "data : ${result.message}")
                }
        }
    }
}

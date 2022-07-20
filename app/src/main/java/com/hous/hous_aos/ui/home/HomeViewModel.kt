package com.hous.hous_aos.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hous.hous_aos.data.entity.Event
import com.hous.hous_aos.data.entity.Homie
import com.hous.hous_aos.data.entity.Rule
import com.hous.hous_aos.data.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {
    private val _eventList = MutableLiveData<List<Event>>()
    val eventList get() = _eventList

    private val _keyRulesList = MutableLiveData<List<String>>()
    val keyRulesList get() = _keyRulesList

    private val _todoList = MutableLiveData<List<Rule>>()
    val todoList get() = _todoList

    private val _homieList = MutableLiveData<List<Homie>>()
    val homieList get() = _homieList

    private val _roomCode = MutableLiveData<String>()

    init {
        viewModelScope.launch {
            homeRepository.getHomeList("")
                .onSuccess { result->
                    Log.d("asdf", "success ${result.message}")
                    _roomCode.value = result.data?.roomCode
                    _eventList.value = result.data?.eventList
                    _todoList.value = result.data?.todoList
                    _keyRulesList.value = result.data?.keyRulesList


                    _homieList.value = result.data?.homieProfileList
                }
                .onFailure { result ->
                    Log.d("asdf", "fail ${result.message}")
                }
        }
    }

    fun roomCode(): String? {
        return _roomCode.value
    }
}

data class HomieProfileList(
    val homie: List<Homie>,
    val roomCode: String
)
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
    private val _eventList = MutableLiveData<List<Event>>(
        listOf(
            Event(
                id = "",
                eventIcon = "",
                dDay = ""
            ),
            Event(
                id = "",
                eventIcon = "PARTY",
                dDay = "12"
            ),
            Event(
                id = "",
                eventIcon = "BEER",
                dDay = "8"
            ),
            Event(
                id = "",
                eventIcon = "COFFEE",
                dDay = "5"
            ),
            Event(
                id = "",
                eventIcon = "CAKE",
                dDay = "3"
            ),
            Event(
                id = "",
                eventIcon = "COFFEE",
                dDay = "2"
            ),
            Event(
                id = "",
                eventIcon = "PARTY",
                dDay = "1"
            ),
        )
    )
    val eventList get() = _eventList

    private val _keyRulesList = MutableLiveData<List<String>>(
        listOf(
//            "상단규칙",
//            "하단규칙"
        )
    )
    val keyRulesList get() = _keyRulesList

    private val _todoList = MutableLiveData<List<Rule>>(
         listOf(
            Rule(
                isChecked = true,
                ruleName = "빨래를돌려야하는데언제돌리지젠장",
            ),
            Rule(
                isChecked = false,
                ruleName = "청소기도돌려야하는데언제하지젠장",
            ),
            Rule(
                isChecked = false,
                ruleName = "커미사마셔야하는데배가아프네젠장",
            )
        )
    )
    val todoList get() = _todoList

    private val _homieList = MutableLiveData<List<Homie>>(
        listOf(
            Homie(
                id = "",
                userName = "이영주",
                typeName = "임시 디폴트",
                typeColor = "YELLOW"
            ),
            Homie(
                id = "",
                userName = "김지현",
                typeName = "임시 디폴트",
                typeColor = "RED"
            ),
            Homie(
                id = "",
                userName = "김민재",
                typeName = "임시 디폴트",
                typeColor = "BLUE"
            ),
            Homie(
                id = "",
                userName = "이준원",
                typeName = "임시 디폴트",
                typeColor = ""
            ),
            Homie(
                id = "",
                userName = "강원용",
                typeName = "임시 디폴트",
                typeColor = "GREEN"
            ),
            //copy room code 더미데이터
            Homie(
                id = "",
                userName = "",
                typeName = "",
                typeColor = ""
            )
        )
    )
    val homieList get() = _homieList

    private val _roomCode = MutableLiveData<String>(
        "85UHIKZB"
    )

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
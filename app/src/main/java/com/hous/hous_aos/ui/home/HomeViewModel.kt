package com.hous.hous_aos.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    private val _eventList = MutableLiveData<List<EventData>>(
        listOf(
            EventData(
                _id = "",
                eventIcon = "NONE",
                dDay = ""
            ),
            EventData(
                _id = "62cdab999b7fca5900cff7df",
                eventIcon = "PARTY",
                dDay = "1"
            ),
            EventData(
                _id = "62cdab999b7fca5900cff7df",
                eventIcon = "COFFEE",
                dDay = "2"
            ),
            EventData(
                _id = "62cdab999b7fca5900cff7df",
                eventIcon = "BEER",
                dDay = "3"
            ),
            EventData(
                _id = "62cdab999b7fca5900cff7df",
                eventIcon = "PARTY",
                dDay = "4"
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

    private val _todoList = MutableLiveData<List<ToDoData>>(
        listOf(
            ToDoData(
                isCheck = false,
                todoName = "빨래를돌려야하는데언제돌리지젠장",
                createdAt = "2022-07-11T20:00:10.985Z"
            ),
            ToDoData(
                isCheck = true,
                todoName = "청소기도돌려야하는데언제하지젠장",
                createdAt = "2022-07-11T20:00:10.985Z"
            ),
            ToDoData(
                isCheck = true,
                todoName = "커미사마셔야하는데배가아프네젠장",
                createdAt = "2022-07-11T20:00:10.985Z"
            )
        )
    )
    val todoList get() = _todoList

    private val _homieList = MutableLiveData<List<HomieData>>(
        listOf(
            HomieData(
                _id = "",
                userName = "이영주",
                typeName = "임시 디폴트",
                typeColor = "GRAY"
            ),
            HomieData(
                _id = "62cc7420d7868591384e4eb0",
                userName = "강원용",
                typeName = "임시 디폴트",
                typeColor = "YELLOW"
            ),
            HomieData(
                _id = "62cc7420d7868591384e4eb0",
                userName = "이준원",
                typeName = "임시 디폴트",
                typeColor = "GREEN"
            ),
            HomieData(
                _id = "62cc7420d7868591384e4eb0",
                userName = "김소현",
                typeName = "임시 디폴트",
                typeColor = "BLUE"
            ),
            //copy room code 더미데이터
            HomieData(
                _id = "",
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

    fun RoomCode(): String? {
        return _roomCode.value
    }
}

data class HomieProfileList(
    val homie: List<HomieData>,
    val roomCode: String
)
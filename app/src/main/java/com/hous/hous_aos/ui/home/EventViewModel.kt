package com.hous.hous_aos.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hous.hous_aos.data.entity.rules.HomieData
import com.hous.hous_aos.data.entity.rules.ResponseEventData

class EventViewModel : ViewModel() {
    private var _eventIconPosition = MutableLiveData<Int>(START_POSITION)
    val eventIconPosition get() = _eventIconPosition

    private val _responseEventData = MutableLiveData<ResponseEventData>()
    val responseEventData get() = _responseEventData

    private val _eventName = MutableLiveData<String>()
    val eventName get() = _eventName

    private val _selectedEvent = MutableLiveData<EventIcon>(EventIcon.FIRST)
    val selectedEvent get() = _selectedEvent

    private var _eventParticipantList = MutableLiveData<List<HomieData>>()
    val eventParticipantList get() = _eventParticipantList

    // TODO 리사이클러뷰에 postion 넘겨주기
    fun setEventIconPosition(position: Int) {
        _eventIconPosition.value = position
    }

    fun setSelectedEvent(event: EventIcon) {
        _selectedEvent.value = event
    }

    fun setEventName() {
        _eventName.value = requireNotNull(_responseEventData.value).eventName
    }

    fun fetchToAddEventData() {
        _eventName.value = ""
        // TODO default 호미들 넣어주기
    }

    fun fetchToResponseEventData() {
        _responseEventData.value = ResponseEventData(
            id = "62d4335e17e70062873f3d28",
            eventName = "파티 파티",
            eventIcon = "PARTY",
            date = "2022-07-28",
            participants = listOf<HomieData>(
                HomieData(
                    id = "62cc7409csdsd06c46adf652f",
                    userName = "이준원",
                    isChecked = true,
                    typeColor = "GRAY"
                ),
                HomieData(
                    id = "6dasdasdasdsadsad52f",
                    userName = "이영주",
                    isChecked = false,
                    typeColor = "RED"
                ),
                HomieData(
                    id = "62cc740asdsadsadf652f",
                    userName = "강워어뇽",
                    isChecked = true,
                    typeColor = "BLUE"
                ),
                HomieData(
                    id = "62cc7409csasdsadsa52f",
                    userName = "꾸우웅",
                    isChecked = false,
                    typeColor = "GREEN"
                ),
                HomieData(
                    id = "62cc7409csasdsadsa52f",
                    userName = "꾸우우웅",
                    isChecked = false,
                    typeColor = "GRAY"
                )
            )
        )
        when (requireNotNull(_responseEventData.value).eventIcon) {
            "PARTY" -> setSelectedEvent(EventIcon.FIRST)
            "CAKE" -> setSelectedEvent(EventIcon.SECOND)
            "BEER" -> setSelectedEvent(EventIcon.THIRD)
            "COFFEE" -> setSelectedEvent(EventIcon.FOURTH)
        }
    }

    /**
     * 이벤트 조회하기
     */
    fun setParticipantList() {
        if (eventIconPosition.value != 0) {
            val tmp = _responseEventData.value?.participants
            _eventParticipantList.value = tmp?.map { it.copy() }
        } else {
            val tmp = _homieList.value
            _eventParticipantList.value = tmp?.map { it.copy() }
        }
    }

    /**
     * 임시 담당자 checked 바꾸기
     * */
    fun setSelectedEventParticipant(position: Int) {
        val flag = requireNotNull(_eventParticipantList.value)[position].isChecked
        requireNotNull(_eventParticipantList.value)[position].isChecked = !flag
    }

    /**
     *  save버튼 누르면 파티 참여자 서버로 보내기
     * */
    fun putToEventParticipant(): List<String> {
        val clickedTmpManagerList: MutableList<String> = mutableListOf()
        _eventParticipantList.value?.forEach {
            if (it.isChecked) clickedTmpManagerList.add(it.id)
        }
        return clickedTmpManagerList.toList()
    }

    /**************************************************************************************************
     *  영주 코드
     */
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
                id = "",
                userName = "이영주",
                typeName = "임시 디폴트",
                typeColor = "GRAY"
            ),
            HomieData(
                id = "62cc7420d7868591384e4eb0",
                userName = "강원용",
                typeName = "임시 디폴트",
                typeColor = "YELLOW"
            ),
            HomieData(
                id = "62cc7420d7868591384e4eb0",
                userName = "이준원",
                typeName = "임시 디폴트",
                typeColor = "GREEN"
            ),
            HomieData(
                id = "62cc7420d7868591384e4eb0",
                userName = "김소현",
                typeName = "임시 디폴트",
                typeColor = "BLUE"
            ),
            // copy room code 더미데이터
            HomieData(
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

    fun RoomCode(): String? {
        return _roomCode.value
    }

    companion object {
        const val START_POSITION = 100
    }
}

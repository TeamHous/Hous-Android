package com.hous.hous_aos.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hous.hous_aos.data.entity.rules.HomieData
import com.hous.hous_aos.data.entity.rules.ResponseEventData

class EventViewModel : ViewModel() {
    private val _responseEventData = MutableLiveData<ResponseEventData>()
    val responseEventData get() = _responseEventData

    private val _eventName = MutableLiveData<String>()
    val eventName get() = _eventName

    private val _selectedEvent = MutableLiveData<EventIcon>(EventIcon.FIRST)
    val selectedEvent get() = _selectedEvent

    private var _eventParticipantList = MutableLiveData<List<HomieData>>()
    val eventParticipantList get() = _eventParticipantList

    init {
        fetchToResponseEventData()
        // check 이번트 처리
    }

    fun setSelectedEvent(event: EventIcon) {
        _selectedEvent.value = event
    }

    fun setEventName() {
        _eventName.value = requireNotNull(_responseEventData.value).eventName
    }

    private fun fetchToResponseEventData() {
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
        val tmp = _responseEventData.value?.participants
        _eventParticipantList.value = tmp?.map { it.copy() }
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
}

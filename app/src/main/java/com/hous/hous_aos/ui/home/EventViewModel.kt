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
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {
    private var _eventDate = MutableLiveData<String>("")
    val eventDate get() = _eventDate

    private var _eventIconPosition = MutableLiveData<Int>(START_POSITION)
    val eventIconPosition get() = _eventIconPosition

    private val _responseEventData = MutableLiveData<Event>()
    val responseEventData get() = _responseEventData

    private val _eventName = MutableLiveData<String>()
    val eventName get() = _eventName

    private val _selectedEvent = MutableLiveData<EventIcon>(EventIcon.FIRST)
    val selectedEvent get() = _selectedEvent

    private var _eventParticipantList = MutableLiveData<List<Homie>>()
    val eventParticipantList get() = _eventParticipantList

    // 받아올 때
    // 2022-07-28
    fun setEventData(date: String) {
        _eventDate.value = date
    }

    fun setEventData() {
        _eventDate.value = responseEventData.value?.date
    }

    /** 리사이클러뷰에 postion 받아오기*/
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
        setParticipantList()
        setCurrentTimeToEventDate()
    }

    /** 현재 시간으로 세팅*/
    fun setCurrentTimeToEventDate() {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ISO_DATE
        _eventDate.value = current.format(formatter)
    }

    fun fetchToResponseEventData() {
        _responseEventData.value = Event(
            id = "62d4335e17e70062873f3d28",
            eventName = "파티 파티",
            eventIcon = "PARTY",
            date = "2022-07-28",
            participants = listOf<Homie>(
                Homie(
                    id = "62cc7409csdsd06c46adf652f",
                    userName = "이준원",
                    isChecked = true,
                    typeColor = "GRAY"
                ),
                Homie(
                    id = "6dasdasdasdsadsad52f",
                    userName = "이영주",
                    isChecked = false,
                    typeColor = "RED"
                ),
                Homie(
                    id = "62cc740asdsadsadf652f",
                    userName = "강워어뇽",
                    isChecked = true,
                    typeColor = "BLUE"
                ),
                Homie(
                    id = "62cc7409csasdsadsa52f",
                    userName = "꾸우웅",
                    isChecked = false,
                    typeColor = "GREEN"
                ),
                Homie(
                    id = "62cc7409csasdsadsa52f",
                    userName = "꾸우우웅",
                    isChecked = false,
                    typeColor = "GRAY"
                )
            )
        )
        when (requireNotNull(responseEventData.value).eventIcon) {
            "PARTY" -> setSelectedEvent(EventIcon.FIRST)
            "CAKE" -> setSelectedEvent(EventIcon.SECOND)
            "BEER" -> setSelectedEvent(EventIcon.THIRD)
            "COFFEE" -> setSelectedEvent(EventIcon.FOURTH)
        }
        setParticipantList()
        setEventName()
        setEventData()
    }

    /**
     * 이벤트 조회하기
     */
    fun setParticipantList() {
        if (eventIconPosition.value != 0) {
            val tmp = responseEventData.value?.participants
            _eventParticipantList.value = tmp?.map { it.copy() }
        } else {
            val tmp = homieList.value
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
        eventParticipantList.value?.forEach {
            if (it.isChecked) clickedTmpManagerList.add(it.id!!)
        }
        return clickedTmpManagerList.toList()
    }

    /**************************************************************************************************
     *  영주 코드
     */
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
                .onSuccess { result ->
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
    companion object {
        const val START_POSITION = 100
    }
}

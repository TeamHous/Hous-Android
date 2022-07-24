package com.hous.hous_aos.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hous.hous_aos.data.entity.Event
import com.hous.hous_aos.data.entity.Homie
import com.hous.hous_aos.data.entity.Rule
import com.hous.hous_aos.data.model.request.EventListRequest
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
    private var _tmpEventId = MutableLiveData<String>("")
    val tmpEventId get() = _tmpEventId

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

    /** get
     * Event 조회
     * 리사이클러뷰에 postion 받아오기*/
    fun getEventDetail(position: Int) {

        viewModelScope.launch {
            Log.d(TAG, "                       position: $position , evenList.value: ${eventList.value}")
            _eventIconPosition.value = position
            _tmpEventId.value = eventList.value!![position].id
            homeRepository.getEventList("", tmpEventId.value!!)
                .onSuccess {
                    Log.d(TAG, "onSuccess - eventId: $tmpEventId.value!!, 성공메세지: ${it.message}")
                    Log.d(TAG, "onSuccess - eventId: $tmpEventId.value!!, 성공메세지: ${it.message}")
                    _responseEventData.value = it.data!!
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
                .onFailure {
                    Log.d(
                        TAG,
                        "onFail - eventId: $tmpEventId.value!!, 오류메세지: ${it.message}"
                    )
                }
        }
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
    /** put
     *  save버튼 누르면 파티 참여자 서버로 보내기
     * */
    fun putToEventParticipant() {
        // 이벤트 put Body 값
        val clickedTmpManagerList: MutableList<String> = mutableListOf()
        eventParticipantList.value?.forEach {
            if (it.isChecked) clickedTmpManagerList.add(it.id!!)
        }
        viewModelScope.launch {
            homeRepository.putEventList(
                "", _tmpEventId.value!!,
                EventListRequest(
                    eventName = eventName.value!!,
                    date = eventDate.value!!,
                    participants = clickedTmpManagerList,
                    eventIcon = selectedEvent.value!!.IconName
                )
            ).onSuccess {
                getEventList()
                Log.d(TAG, "이벤트 수정 Success - ${it.message}")
            }.onFailure {
                Log.d(TAG, "이벤트 수정 Fail - ${it.message}")
            }
        }
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
    val roomCode get() = _roomCode

    fun homeInfo() {
        viewModelScope.launch {
            homeRepository.getHomeList("")
                .onSuccess { result ->
                    Log.d("asdf", "success ${result.data}")
                    val tempEventList = mutableListOf(Event())
                    result.data!!.eventList.forEach { tempEventList.add(it) }
                    _eventList.value = tempEventList
                    _roomCode.value = result.data!!.roomCode
                    _todoList.value = result.data!!.todoList
                    _keyRulesList.value = result.data!!.keyRulesList
                    _homieList.value = result.data!!.homieProfileList
                    val tempHomieList = mutableListOf(Homie())
                    _homieList.value = _homieList.value?.plus(tempHomieList)
                }
                .onFailure { result ->
                    Log.d("asdf", "fail ${result.message}")
                }
        }
    }

    fun addToEventParticipant() {
        val clickedTmpManagerList: MutableList<String> = mutableListOf()
        eventParticipantList.value?.forEach {
            if (it.isChecked) clickedTmpManagerList.add(it.id!!)
        }
        viewModelScope.launch {
            homeRepository.addEvent(
                "",
                EventListRequest(
                    eventName = eventName.value!!,
                    date = eventDate.value!!,
                    participants = clickedTmpManagerList,
                    eventIcon = selectedEvent.value!!.IconName
                )
            )
                .onSuccess {
                    Log.d("EventViewModel", "이벤트 삭제 성공 : ${it.message}")
                    getEventList()
                }
                .onFailure { Log.d("EventViewModel", "이벤트 삭제 실패 : ${it.message}") }
        }
    }

    fun deleteEventItem() {
        viewModelScope.launch {
            homeRepository.deleteEvent("", _tmpEventId.value!!)
                .onSuccess {
                    Log.d("EventViewModel", "이벤트 삭제 성공 : ${it.message}")
                    getEventList()
                }
                .onFailure { Log.d("EventViewModel", "이벤트 삭제 실패 : ${it.message}") }
        }
    }

    private suspend fun getEventList() {
        homeRepository.getHomeList("").onSuccess { result ->
            Log.d("asdf", "success ${result.message}")
            val tempEventList = mutableListOf(Event())
            result.data!!.eventList.forEach { tempEventList.add(it) }
            _eventList.value = tempEventList
        }.onFailure { result ->
            Log.d("asdf", "fail ${result.message}")
        }
    }

    companion object {
        private const val TAG = "eventViewModel"
        val START_POSITION = 100
    }
}

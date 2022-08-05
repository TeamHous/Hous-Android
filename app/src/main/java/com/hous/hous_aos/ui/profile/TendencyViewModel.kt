package com.hous.hous_aos.ui.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hous.data.model.request.PutTestResultRequest
import com.hous.data.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class TendencyViewModel @Inject constructor(
    private val profileRepository: com.hous.data.repository.ProfileRepository
) : ViewModel() {
    private val _move = MutableLiveData<Boolean>()
    val move: LiveData<Boolean> = _move
    private val _uiState = MutableStateFlow(TestUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            profileRepository.getTypeTestList()
                .onSuccess {
                    Log.d("TendencyViewModel", "success ${it.data!!.typeTests}")
                    val tempList = it.data.typeTests.map { typeTest ->
                        com.hous.data.entity.TypeTest(
                            id = typeTest.id,
                            testNum = typeTest.testNum,
                            question = typeTest.question,
                            questionType = typeTest.questionType,
                            answers = typeTest.answers,
                            questionImg = typeTest.questionImg,
                            type = com.hous.data.entity.TypeState.NONE
                        )
                    }
                    _uiState.value = _uiState.value.copy(typeTests = tempList)
                }
                .onFailure {
                    Log.d("TendencyViewModel", "fail ${it.message}")
                }
        }
    }

    fun select(position: Int, state: com.hous.data.entity.TypeState) {
        viewModelScope.launch {
            val tempTest = mutableListOf<com.hous.data.entity.TypeTest>()
            _uiState.value.typeTests.forEach { tempTest.add(it) }
            val tempTypeTest = com.hous.data.entity.TypeTest(
                id = _uiState.value.typeTests[position].id,
                question = _uiState.value.typeTests[position].question,
                questionType = _uiState.value.typeTests[position].questionType,
                questionImg = _uiState.value.typeTests[position].questionImg,
                answers = _uiState.value.typeTests[position].answers,
                testNum = _uiState.value.typeTests[position].testNum,
                type = state
            )
            tempTest[position] = tempTypeTest
            _uiState.value = _uiState.value.copy(typeTests = tempTest)
            delay(300L)
            _move.value = true
        }
    }

    fun backPage() {
        _move.value = false
    }

    fun nextPage() {
        _move.value = true
    }

    fun sendData() {
        viewModelScope.launch {
            sumScore()
            profileRepository.putTestResult(com.hous.data.model.request.PutTestResultRequest(uiState.value.answerHolder))
                .onSuccess { Log.d("TendencyViewModel", "result success : ${it.message}") }
                .onFailure { Log.d("TendencyViewModel", "fail : ${it.message}") }
        }
    }

    private fun sumScore() {
        val tempAnswer = mutableListOf(0, 0, 0, 0, 0)
        for (index in 0..14) {
            tempAnswer[index / 3] += getScore(uiState.value.typeTests[index].type)
        }
        _uiState.value = _uiState.value.copy(answerHolder = tempAnswer)
    }

    private fun getScore(state: com.hous.data.entity.TypeState): Int = when (state) {
        com.hous.data.entity.TypeState.ONE -> 1
        com.hous.data.entity.TypeState.TWO -> 2
        com.hous.data.entity.TypeState.THREE -> 3
        com.hous.data.entity.TypeState.NONE -> 0
    }
}

data class TestUiState(
    val typeTests: List<com.hous.data.entity.TypeTest> = listOf(),
    val answerHolder: List<Int> = listOf(0, 0, 0, 0, 0)
)

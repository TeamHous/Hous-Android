package com.hous.hous_aos.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TendencyViewModel : ViewModel() {
    private val _move = MutableLiveData<Boolean>()
    val move: LiveData<Boolean> = _move
    private val _uiState = MutableStateFlow(TestUiState())
    val uiState = _uiState.asStateFlow()

    fun select(position: Int, state: TypeState) {
        viewModelScope.launch {
            val tempTest = mutableListOf<TypeTest>()
            _uiState.value.typeTests.forEach { tempTest.add(it) }
            val tempTypeTest = TypeTest(
                _id = _uiState.value.typeTests[position]._id,
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
}

data class TestUiState(
    val typeTests: List<TypeTest> = listOf(
        TypeTest(
            _id = "",
            testNum = 1,
            question = "",
            questionType = "",
            answers = listOf("test1", "test2", "test3"),
            questionImg = ""
        ),
        TypeTest(
            _id = "",
            testNum = 2,
            question = "",
            questionType = "",
            answers = listOf("test1", "test2", "test3"),
            questionImg = ""
        ),
        TypeTest(
            _id = "",
            testNum = 3,
            question = "",
            questionType = "",
            answers = listOf("test1", "test2", "test3"),
            questionImg = ""
        ),
        TypeTest(
            _id = "",
            testNum = 4,
            question = "",
            questionType = "",
            answers = listOf("test1", "test2", "test3"),
            questionImg = ""
        ),
        TypeTest(
            _id = "",
            testNum = 5,
            question = "",
            questionType = "",
            answers = listOf("test1", "test2", "test3"),
            questionImg = ""
        ),
        TypeTest(
            _id = "",
            testNum = 6,
            question = "",
            questionType = "",
            answers = listOf("test1", "test2", "test3"),
            questionImg = ""
        ),
        TypeTest(
            _id = "",
            testNum = 7,
            question = "",
            questionType = "",
            answers = listOf("test1", "test2", "test3"),
            questionImg = ""
        ),
        TypeTest(
            _id = "",
            testNum = 8,
            question = "",
            questionType = "",
            answers = listOf("test1", "test2", "test3"),
            questionImg = ""
        ),
        TypeTest(
            _id = "",
            testNum = 9,
            question = "",
            questionType = "",
            answers = listOf("test1", "test2", "test3"),
            questionImg = ""
        ),
        TypeTest(
            _id = "",
            testNum = 10,
            question = "",
            questionType = "",
            answers = listOf("test1", "test2", "test3"),
            questionImg = ""
        ),
    ),
    val answerHolder: List<Int> = listOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
)

package com.hous.hous_aos.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TendencyViewModel : ViewModel() {
    private val _move = MutableLiveData<Boolean>()
    val move: LiveData<Boolean> = _move
    private val _uiState = MutableStateFlow(TestUiState())
    val uiState = _uiState.asStateFlow()
}

data class TestUiState(
    val totalIndex: Int = 0,
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

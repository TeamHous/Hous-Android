package com.hous.hous_aos.ui.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hous.hous_aos.data.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class TendencyViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {
    private val _move = MutableLiveData<Boolean>()
    val move: LiveData<Boolean> = _move
    private val _uiState = MutableStateFlow(TestUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            profileRepository.getTypeTestList()
                .onSuccess {
                    Log.d("TendencyViewModel", "success ${it.data}")
                    _uiState.value = _uiState.value.copy(typeTests = it.data!!)
                }
                .onFailure {
                    Log.d("TendencyViewModel", "fail ${it.message}")
                }
        }
    }

    fun select(position: Int, state: TypeState) {
        viewModelScope.launch {
            val tempTest = mutableListOf<TypeTest>()
            _uiState.value.typeTests.forEach { tempTest.add(it) }
            val tempTypeTest = TypeTest(
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
            Log.d("TendencyViewModel", "result : ${uiState.value.answerHolder}")
            profileRepository.putTestResult(uiState.value.answerHolder)
        }
    }

    private fun sumScore() {
        val tempAnswer = mutableListOf(0, 0, 0, 0, 0)
        for (index in 0..14) {
            tempAnswer[index / 3] += getScore(uiState.value.typeTests[index].type)
        }
        _uiState.value = _uiState.value.copy(answerHolder = tempAnswer)
    }

    private fun getScore(state: TypeState): Int = when (state) {
        TypeState.ONE -> 1
        TypeState.TWO -> 2
        TypeState.THREE -> 3
        TypeState.NONE -> 0
    }
}

data class TestUiState(
    val typeTests: List<TypeTest> = listOf(
        TypeTest(
            id = "",
            testNum = 1,
            question = "",
            questionType = "",
            answers = listOf("test1", "test2", "test3"),
            questionImg = "https://team-hous.s3.ap-northeast-2.amazonaws.com/Type/test/type_test_1.png"
        ),
        TypeTest(
            id = "",
            testNum = 2,
            question = "",
            questionType = "",
            answers = listOf("test1", "test2", "test3"),
            questionImg = "https://team-hous.s3.ap-northeast-2.amazonaws.com/Type/test/type_test_1.png"
        ),
        TypeTest(
            id = "",
            testNum = 3,
            question = "",
            questionType = "",
            answers = listOf("test1", "test2", "test3"),
            questionImg = "https://team-hous.s3.ap-northeast-2.amazonaws.com/Type/test/type_test_1.png"
        ),
        TypeTest(
            id = "",
            testNum = 4,
            question = "",
            questionType = "",
            answers = listOf("test1", "test2", "test3"),
            questionImg = "https://team-hous.s3.ap-northeast-2.amazonaws.com/Type/test/type_test_1.png"
        ),
        TypeTest(
            id = "",
            testNum = 5,
            question = "",
            questionType = "",
            answers = listOf("test1", "test2", "test3"),
            questionImg = "https://team-hous.s3.ap-northeast-2.amazonaws.com/Type/test/type_test_1.png"
        ),
        TypeTest(
            id = "",
            testNum = 6,
            question = "",
            questionType = "",
            answers = listOf("test1", "test2", "test3"),
            questionImg = "https://team-hous.s3.ap-northeast-2.amazonaws.com/Type/test/type_test_1.png"
        ),
        TypeTest(
            id = "",
            testNum = 7,
            question = "",
            questionType = "",
            answers = listOf("test1", "test2", "test3"),
            questionImg = "https://team-hous.s3.ap-northeast-2.amazonaws.com/Type/test/type_test_1.png"
        ),
        TypeTest(
            id = "",
            testNum = 8,
            question = "",
            questionType = "",
            answers = listOf("test1", "test2", "test3"),
            questionImg = "https://team-hous.s3.ap-northeast-2.amazonaws.com/Type/test/type_test_1.png"
        ),
        TypeTest(
            id = "",
            testNum = 9,
            question = "",
            questionType = "",
            answers = listOf("test1", "test2", "test3"),
            questionImg = "https://team-hous.s3.ap-northeast-2.amazonaws.com/Type/test/type_test_1.png"
        ),
        TypeTest(
            id = "",
            testNum = 10,
            question = "",
            questionType = "",
            answers = listOf("test1", "test2", "test3"),
            questionImg = "https://team-hous.s3.ap-northeast-2.amazonaws.com/Type/test/type_test_1.png"
        ),
        TypeTest(
            id = "",
            testNum = 11,
            question = "",
            questionType = "",
            answers = listOf("test1", "test2", "test3"),
            questionImg = "https://team-hous.s3.ap-northeast-2.amazonaws.com/Type/test/type_test_1.png"
        ),
        TypeTest(
            id = "",
            testNum = 12,
            question = "",
            questionType = "",
            answers = listOf("test1", "test2", "test3"),
            questionImg = "https://team-hous.s3.ap-northeast-2.amazonaws.com/Type/test/type_test_1.png"
        ),
        TypeTest(
            id = "",
            testNum = 13,
            question = "",
            questionType = "",
            answers = listOf("test1", "test2", "test3"),
            questionImg = "https://team-hous.s3.ap-northeast-2.amazonaws.com/Type/test/type_test_1.png"
        ),
        TypeTest(
            id = "",
            testNum = 14,
            question = "",
            questionType = "",
            answers = listOf("test1", "test2", "test3"),
            questionImg = "https://team-hous.s3.ap-northeast-2.amazonaws.com/Type/test/type_test_1.png"
        ),
        TypeTest(
            id = "",
            testNum = 15,
            question = "",
            questionType = "",
            answers = listOf("test1", "test2", "test3"),
            questionImg = "https://team-hous.s3.ap-northeast-2.amazonaws.com/Type/test/type_test_1.png"
        )
    ),
    val answerHolder: List<Int> = listOf(0, 0, 0, 0, 0)
)

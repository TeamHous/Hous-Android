package com.hous.hous_aos.ui.rules

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hous.hous_aos.data.entity.Category
import com.hous.hous_aos.data.entity.Homie
import com.hous.hous_aos.data.entity.Rule
import com.hous.hous_aos.data.model.request.MyToDoCheckRequest
import com.hous.hous_aos.data.model.response.TempManagerRequest
import com.hous.hous_aos.data.repository.RulesTodayRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RulesViewModel @Inject constructor(
    private val rulesTodayRepository: RulesTodayRepository
) : ViewModel() {
    private var _toDoViewType = MutableLiveData(ToDoViewType.TODAY_TO_DO)
    val toDoViewType: LiveData<ToDoViewType> get() = _toDoViewType

    private var _isSelectedCategorySmile = MutableLiveData<Boolean>(true)
    val isSelectedCategorySmile: LiveData<Boolean> get() = _isSelectedCategorySmile

    private var _categoryOfRuleList =
        MutableLiveData<List<Category>>()
    val categoryOfRuleList get() = _categoryOfRuleList

    private var _todayTodoList =
        MutableLiveData<List<Rule>>()
    val todayTodoList get() = _todayTodoList

    private var _myTodoList =
        MutableLiveData<List<Rule>>()
    val myTodoList get() = _myTodoList

    private var _keyRulesTableList =
        MutableLiveData<List<Rule>>()
    val keyRulesTableList get() = _keyRulesTableList

    private var _generalRulesTableList =
        MutableLiveData<List<Rule>>()
    val generalRulesTableList get() = _generalRulesTableList

    private var _tmpManagerList = MutableLiveData<List<Homie>>()
    val tmpManagerList get() = _tmpManagerList

    private var _tmpTodayToDoPosition = MutableLiveData<Int>(0)
    val tmpTodayToDoPosition get() = _tmpTodayToDoPosition

    init {
        viewModelScope.launch {
            rulesTodayRepository.getTodayTodayInfoList("")
                .onSuccess {
                    _todayTodoList.value = it.data!!.todayTodoRules
                    _categoryOfRuleList.value = it.data.homeRuleCategories
                }
                .onFailure {
                    Log.d(TAG, "RulesViewModel - init - getRulesTodayList fail : ${it.message}")
                }
        }
    }

    /**
     * Put
     * 임시 담당자 save버튼 누르면 서버로 보내기
     * */
    fun putToTmpManagerList() {
        val clickedTmpManagerList: MutableList<String> = mutableListOf()
        _tmpManagerList.value?.forEach {
            if (it.isChecked) clickedTmpManagerList.add(it.id!!)
        }
        val tmp = TempManagerRequest(clickedTmpManagerList)
        Log.d(
            "리스트리스트",
            "Put -- tmp.tmpRuleMembers: ${tmp.tmpRuleMembers} tmp.size : ${tmp.tmpRuleMembers.size}"
        )
        viewModelScope.launch {
            rulesTodayRepository.putTempManagerInfoList(
                "",
                _todayTodoList.value!![tmpTodayToDoPosition.value!!].id,
                tmp
            )
                .onSuccess {
                    fetchToTodayToDoList()
                }
                .onFailure {
                    Log.d(TAG, " result fail : $tmp")
                    Log.d(TAG, " result fail : ${it.message}")
                }
        }
    }

    /**
     * 임시 담당자 checked 바꾸기
     * */
    fun setSelectedTmpManager(position: Int) {
        requireNotNull(_tmpManagerList.value)[position].isChecked =
            !requireNotNull(_tmpManagerList.value)[position].isChecked
    }

    /** get
     * 임시 담당자 다이얼로그 조회 */
    fun fetchToTmpManagerList(position: Int) {
        Log.d(
            TAG,
            "RulesViewModel - fetchToTmpManagerList() _todayTodoList.value!![position].id: ${_todayTodoList.value!![position].id}"
        )
        viewModelScope.launch {
            rulesTodayRepository.getTempManagerInfoList("", _todayTodoList.value!![position].id)
                .onSuccess {
                    _tmpManagerList.value = it.data!!.homies
                    _tmpTodayToDoPosition.postValue(position)
                }
                .onFailure {
                    Log.d(TAG, "RulesViewModel - fetchToTmpManagerList() - ${it.message}")
                }
        }
    }

    fun fetchToTodayToDoList() {
        viewModelScope.launch {
            rulesTodayRepository.getTodayTodayInfoList("")
                .onSuccess {
                    _todayTodoList.value = it.data!!.todayTodoRules
                    Log.d(
                        "리스트리스트",
                        "다시서버통신 -- Size: ${_todayTodoList?.value!![0].todayMembersWithTypeColor.size}"
                    )
                }
                .onFailure {
                    Log.d(
                        "리스트리스트",
                        "RulesViewModel - init - getRulesTodayList fail : ${it.message}"
                    )
                }
        }
    }

    /** get
     * My -To - DO 서버통신*/
    fun fetchToMyTodayToDoList() {
        viewModelScope.launch {
            rulesTodayRepository.getMyTodoInfoList("")
                .onSuccess {
                    _myTodoList.value = it.data!!
                    Log.d(TAG, "RulesViewModel - fetchToMyTodayToDoList() called")
                    Log.d("MYTODO", "Success ")
                }
                .onFailure {
                    Log.d("MYTODO", "fail : ${it.message}")
                }
        }
    }

    /** put
     * 나의 to-do check한 거 보내기
     * */
    fun setMyToDoCheckBoxSelected(position: Int) {
        val isSelected = myTodoList.value!![position].isChecked
        myTodoList.value!![position].isChecked = !isSelected
        val checked = myTodoList.value!![position].isChecked
        val id = myTodoList.value!![position].id
        viewModelScope.launch {
            rulesTodayRepository.putMyToDoCheckLust("", id, MyToDoCheckRequest(checked))
                .onSuccess {
                    Log.d("MYTODO", "Success - id: $id, checked: $checked ")
                }
                .onFailure {
                    Log.d(
                        "MYTODO",
                        "fail - $id, checked: $checked -  :${it.message}"
                    )
                }
        }
    }

    /** Rules Table 일반 rules*/
    fun fetchToGeneralRulesTableList() {
        val tmp = listOf(
            Rule(
                id = "62cc74e8dasdasd591384e4ecd",
                ruleName = "설거지하기",
                membersCnt = 2,
                typeColors = listOf("GRAY", "RED")
            ),
            Rule(
                id = "62ccsad209asdasd00f",
                ruleName = "청소하기",
                membersCnt = 4,
                typeColors = listOf(
                    "GRAY",
                    "GREEN",
                    "RED"
                )
            ),
            Rule(
                id = "62ccasdsadsasd00f",
                ruleName = "바퀴벌레 잡기",
                membersCnt = 3,
                typeColors = listOf(
                    "PURPLE",
                    "YELLOW",
                    "RED"
                )
            ),
            Rule(
                id = "62ccsasdasdcdcfa7asdsad0f",
                ruleName = "쓰레기 구분",
                membersCnt = 1,
                typeColors = listOf("PURPLE")
            ),
            Rule(
                id = "62cd4easdsaee723123dawsd",
                ruleName = "임시 담당자 규칙",
                membersCnt = 0,
                typeColors = listOf()
            )
        )
        _generalRulesTableList.value = tmp.map { it.copy() }
    }

    /** Rules Table key rules*/
    fun fetchToKeyRulesTableList() {
        val tmp = listOf(
            Rule(
                id = "62cc81ac1a034f0287c5c6ec",
                ruleName = "상단규칙"
            )
        )
        _keyRulesTableList.value = tmp.map { it.copy() }
    }

    fun setToDoViewType(toDoViewType: ToDoViewType) {
        _toDoViewType.value = toDoViewType
    }

    fun setSmileSelected(selected: Boolean) {
        _isSelectedCategorySmile.value = selected
    }

    fun initCategorySelected() {
        val tmpCategoryOfRuleList = requireNotNull(_categoryOfRuleList.value).map { data ->
            data.copy().apply { isChecked = false }
        }
        Log.d(TAG, "RulesViewModel - initCategorySelected() called")
        tmpCategoryOfRuleList.toMutableList().apply {
            add(
                Category(
                    id = "62d6b94e0e9be86f165d48db",
                    categoryName = "청소",
                    categoryIcon = "CLEAN"
                )
            )
        }

        _categoryOfRuleList.value = tmpCategoryOfRuleList.toList()
    }

    fun setCategorySelected(position: Int) {
        val tmpCategoryOfRuleList = requireNotNull(_categoryOfRuleList.value).map { data ->
            data.copy().apply { isChecked = false }
        }
        tmpCategoryOfRuleList[position].isChecked = true
        _categoryOfRuleList.value = tmpCategoryOfRuleList.toList()
    }

    companion object {
        private const val TAG = "rulesViewmodel"
    }
}

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

    private var _ruleTableSize = MutableLiveData<Int>(0)
    val ruleTableSize get() = _ruleTableSize

    private var _categoryName = MutableLiveData("")
    val categoryName get() = _categoryName

    private var _categoryId = MutableLiveData("")
    val categoryId get() = _categoryId

    init {
        viewModelScope.launch {
            rulesTodayRepository.getTodayTodayInfoList("")
                .onSuccess {
                    _todayTodoList.value = it.data!!.todayTodoRules
                    _categoryOfRuleList.value = it.data.homeRuleCategories
                    _categoryOfRuleList.value = (_categoryOfRuleList.value!!).plus(
                        Category(
                            id = "62d6b94e0e9be86f165d48db",
                            categoryName = "??????",
                            categoryIcon = "CLEAN"
                        )
                    )
                    Log.d(
                        TAG,
                        "?????? ?????? -- ${_todayTodoList?.value}"
                    )
                }
                .onFailure {
                    Log.d(TAG, "RulesViewModel - init - getRulesTodayList fail : ${it.message}")
                }
        }
    }

    /**
     * Put
     * ?????? ????????? save?????? ????????? ????????? ?????????
     * */
    fun putToTmpManagerList() {
        val clickedTmpManagerList: MutableList<String> = mutableListOf()
        _tmpManagerList.value?.forEach {
            if (it.isChecked) clickedTmpManagerList.add(it.id!!)
        }
        val tmp = TempManagerRequest(clickedTmpManagerList)
        Log.d(
            TAG,
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
     * ?????? ????????? checked ?????????
     * */
    fun setSelectedTmpManager(position: Int) {
        requireNotNull(_tmpManagerList.value)[position].isChecked =
            !requireNotNull(_tmpManagerList.value)[position].isChecked
    }

    /** get
     * ?????? ????????? ??????????????? ?????? */
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
//                    Log.d(
//                        TAG,
//                        "?????????????????? -- Size: ${_todayTodoList?.value!![0].todayMembersWithTypeColor.size}"
//                    )
                }
                .onFailure {
                    Log.d(
                        TAG,
                        "RulesViewModel - init - getRulesTodayList fail : ${it.message}"
                    )
                }
        }
    }

    /** get
     * My -To - DO ????????????*/
    fun fetchToMyTodayToDoList() {
        viewModelScope.launch {
            rulesTodayRepository.getMyTodoInfoList("")
                .onSuccess {
                    _myTodoList.value = it.data!!
                    Log.d(TAG, "RulesViewModel - fetchToMyTodayToDoList() called")
                    Log.d("MYTODO", "Success ")
                }
                .onFailure {
                    Log.d(TAG, "fail : ${it.message}")
                }
        }
    }

    /** put
     * ?????? to-do check??? ??? ?????????
     * */
    fun setMyToDoCheckBoxSelected(position: Int) {
        val isSelected = myTodoList.value!![position].isChecked
        myTodoList.value!![position].isChecked = !isSelected
        val checked = myTodoList.value!![position].isChecked
        val id = myTodoList.value!![position].id
        viewModelScope.launch {
            rulesTodayRepository.putMyToDoCheckLust("", id, MyToDoCheckRequest(checked))
                .onSuccess {
                    Log.d(TAG, "Success - id: $id, checked: $checked ")
                }
                .onFailure {
                    Log.d(
                        TAG,
                        "fail - $id, checked: $checked -  :${it.message}"
                    )
                }
        }
    }

    /**
     * get
     * ???????????? ????????? ????????? Rule Table ????????????*/
    fun fetchToRulesTableList(position: Int) {
        val categoryId = categoryOfRuleList.value!![position].id
        _categoryName.value = categoryOfRuleList.value!![position].categoryName
        _categoryId.value = categoryOfRuleList.value!![position].id
        Log.d("RulesViewModel", "categoryId: $categoryId")
        viewModelScope.launch {
            rulesTodayRepository.getRuleTableInfoList("", categoryId)
                .onSuccess {
                    Log.d("RulesViewModel", "Success - RulesTableList() ${it.message}")
                    val data = it.data
                    Log.d("RulesViewModel", "Success- data: $data")
                    val tmpGeneralRulesTableList = data!!.rules
                    val tmpKeyRulesTableList = data.keyRules
                    val totalRulesDataSize =
                        tmpGeneralRulesTableList.size + tmpKeyRulesTableList.size

                    _ruleTableSize.value = totalRulesDataSize
                    _generalRulesTableList.value = tmpGeneralRulesTableList
                    _keyRulesTableList.value = tmpKeyRulesTableList
                    Log.d(
                        "RulesViewModel",
                        "Success -keyRulesTableList: ${generalRulesTableList.value}"
                    )
                    Log.d(
                        "RulesViewModel",
                        "Success -tmpGeneralRulesTableList: ${keyRulesTableList.value}"
                    )
                }
                .onFailure {
                    Log.d("RulesViewModel", "Fail - RulesTableList() ${it.message}")
                }
        }
    }

    fun setToDoViewType(toDoViewType: ToDoViewType) {
        _toDoViewType.value = toDoViewType
    }

    fun setSmileSelected(selected: Boolean) {
        _isSelectedCategorySmile.value = selected
        fetchToTodayToDoList()
    }

    /**
     * ????????? ?????? or ??????????????? ???????????? ??? ?????? ???????????? name ?????????
     * */
    fun initCategorySelected() {
        if (_categoryOfRuleList.value == null) {
            return
        }
        val tmpCategoryOfRuleList = requireNotNull(_categoryOfRuleList.value).map { data ->
            data.copy().apply { isChecked = false }
        }

        _categoryOfRuleList.value = tmpCategoryOfRuleList
    }

    fun setCategorySelected(position: Int) {
        val tmpCategoryOfRuleList = requireNotNull(_categoryOfRuleList.value).map { data ->
            data.copy().apply { isChecked = false }
        }
        tmpCategoryOfRuleList[position].isChecked = true
        _categoryOfRuleList.value = tmpCategoryOfRuleList.toList()
    }

    companion object {
        private const val TAG = "RulesViewModel"
    }
}

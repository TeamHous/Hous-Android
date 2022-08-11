package com.hous.hous_aos.ui.rules

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hous.data.repository.RulesTodayRepository
import com.hous.domain.model.CategoryInfo
import com.hous.domain.model.HomieInfo
import com.hous.domain.model.RuleInfo
import com.hous.domain.model.TempManagerInfo
import com.hous.domain.model.rules.RulesTodayInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RulesViewModel @Inject constructor(
    private val rulesTodayRepository: RulesTodayRepository
) : ViewModel() {
    private var _toDoViewType = MutableLiveData(ToDoViewType.TODAY_TO_DO)
    val toDoViewType: LiveData<ToDoViewType> get() = _toDoViewType

    private var _isSelectedCategorySmile = MutableLiveData(true)
    val isSelectedCategorySmile: LiveData<Boolean> get() = _isSelectedCategorySmile

    private var _categoryOfRuleList =
        MutableLiveData<List<CategoryInfo>>()
    val categoryOfRuleList get() = _categoryOfRuleList

    private var _todayTodoList =
        MutableLiveData<List<RuleInfo>>()
    val todayTodoList get() = _todayTodoList

    private var _myTodoList =
        MutableLiveData<List<RuleInfo>>()
    val myTodoList get() = _myTodoList

    private var _keyRulesTableList =
        MutableLiveData<List<RuleInfo>>()
    val keyRulesTableList get() = _keyRulesTableList

    private var _generalRulesTableList =
        MutableLiveData<List<RuleInfo>>()
    val generalRulesTableList get() = _generalRulesTableList

    private var _tmpManagerList = MutableLiveData<List<HomieInfo>>()
    val tmpManagerList get() = _tmpManagerList

    private var _tmpTodayToDoPosition = MutableLiveData<Int>(0)
    val tmpTodayToDoPosition get() = _tmpTodayToDoPosition

    private var _ruleTableSize = MutableLiveData(0)
    val ruleTableSize get() = _ruleTableSize

    private var _categoryName = MutableLiveData("")
    val categoryName get() = _categoryName

    private var _categoryId = MutableLiveData("")
    val categoryId get() = _categoryId

    init {
        viewModelScope.launch {
            val rulesTodayInfo: RulesTodayInfo? = rulesTodayRepository.getTodayTodayInfoList("")
            rulesTodayInfo?.let {
                Timber.d("RulesViewModel init")
                _todayTodoList.value = it.todayTodoRules
                _categoryOfRuleList.value = it.homeRuleCategories.plus(
                    CategoryInfo(
                        id = "62d6b94e0e9be86f165d48db",
                        categoryName = "없음",
                        categoryIcon = "CLEAN"
                    )
                )
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

        viewModelScope.launch {
            rulesTodayRepository.putTempManagerInfoList(
                "",
                _todayTodoList.value!![tmpTodayToDoPosition.value!!].id,
                clickedTmpManagerList
            )?.let { fetchToTodayToDoList() }
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
        viewModelScope.launch {
            val tempManagerInfo: TempManagerInfo? =
                rulesTodayRepository.getTempManagerInfoList("", _todayTodoList.value!![position].id)
            tempManagerInfo?.let {
                _tmpManagerList.value = it.homies
                _tmpTodayToDoPosition.value = position
            }

        }
    }

    fun fetchToTodayToDoList() =
        viewModelScope.launch {
            rulesTodayRepository.getTodayTodayInfoList("")?.let {
                _todayTodoList.value = it.todayTodoRules
            }
        }


    /** get
     * My -To - DO 서버통신*/
    fun fetchToMyTodayToDoList() {
        viewModelScope.launch {
            rulesTodayRepository.getMyTodoInfoList("")?.let {
                _myTodoList.value = it
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
            rulesTodayRepository.putMyToDoCheckLust(
                "",
                id,
                checked
            )
        }
    }

    /**
     * get
     * 카테고리 아이콘 클릭시 Rule Table 받아오기*/
    fun fetchToRulesTableList(position: Int) {
        val categoryId = categoryOfRuleList.value!![position].id
        _categoryName.value = categoryOfRuleList.value!![position].categoryName
        _categoryId.value = categoryOfRuleList.value!![position].id
        viewModelScope.launch {
            rulesTodayRepository.getRuleTableInfoList("", categoryId)?.let {
                val tmpGeneralRulesTableList = it.rules
                val tmpKeyRulesTableList = it.keyRules
                val totalRulesDataSize =
                    tmpGeneralRulesTableList.size + tmpKeyRulesTableList.size

                _ruleTableSize.value = totalRulesDataSize
                _generalRulesTableList.value = tmpGeneralRulesTableList
                _keyRulesTableList.value = tmpKeyRulesTableList
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
     * 스마일 버튼 or 바텀네비로 돌아왔을 때 상단 카테고리 name 없애기
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

}

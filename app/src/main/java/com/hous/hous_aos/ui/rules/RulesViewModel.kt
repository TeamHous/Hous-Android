package com.hous.hous_aos.ui.rules

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hous.hous_aos.R
import com.hous.hous_aos.data.entity.Homie
import com.hous.hous_aos.data.entity.Rule
import com.hous.hous_aos.data.entity.rules.*

class RulesViewModel : ViewModel() {
    private var _toDoViewType = MutableLiveData(ToDoViewType.TODAY_TO_DO)
    val toDoViewType: LiveData<ToDoViewType> get() = _toDoViewType

    private var _isSelectedCategorySmile = MutableLiveData<Boolean>(true)
    val isSelectedCategorySmile: LiveData<Boolean> get() = _isSelectedCategorySmile

    private var _categoryOfRuleList =
        MutableLiveData<List<CategoryOfRuleResponse>>()
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

    private var _tmpManagerList = MutableLiveData<List<HomieData>>()
    val tmpManagerList get() = _tmpManagerList

    /**
     * 임시 담당자 checked 바꾸기
     * */
    fun setSelectedTmpManager(position: Int) {
        val flag = requireNotNull(_tmpManagerList.value)[position].isChecked
        requireNotNull(_tmpManagerList.value)[position].isChecked = !flag
    }

    /**
     * 임시 담당자 save버튼 누르면 서버로 보내기
     * */
    fun putToTmpManagerList(): List<String> {
        val clickedTmpManagerList: MutableList<String> = mutableListOf()
        _tmpManagerList.value?.forEach {
            if (it.isChecked) clickedTmpManagerList.add(it.id)
        }
        return clickedTmpManagerList.toList()
    }

    fun fetchToTmpManagerList() {
        val tmp = listOf(
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
        _tmpManagerList.value = tmp.map { it.copy() }
    }

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

    fun fetchToKeyRulesTableList() {
        val tmp = listOf(
            Rule(
                id = "62cc81ac1a034f0287c5c6ec",
                ruleName = "상단규칙"
            )
        )
        _keyRulesTableList.value = tmp.map { it.copy() }
    }

    fun fetchToCategoryOfRuleList() {
        _categoryOfRuleList.value = mutableListOf(
            CategoryOfRuleResponse(
                name = "라면을",
                icon = R.drawable.ic_rules_heart_s,
                backGround = R.drawable.ic_rules_category_red_bg_m
            ),
            CategoryOfRuleResponse(
                name = "너무",
                icon = R.drawable.ic_rules_heart_s,
                backGround = R.drawable.ic_rules_category_red_bg_m
            ),
            CategoryOfRuleResponse(
                name = "많이",
                icon = R.drawable.ic_rules_broom_s,
                backGround = R.drawable.ic_rules_category_blue_bg_m
            ),
            CategoryOfRuleResponse(
                name = "먹었더니",
                icon = R.drawable.ic_rules_heart_s,
                backGround = R.drawable.ic_rules_category_red_bg_m
            ),
            CategoryOfRuleResponse(
                name = "배불러",
                icon = R.drawable.ic_rules_heart_s,
                backGround = R.drawable.ic_rules_category_red_bg_m
            ),
            CategoryOfRuleResponse(
                name = "",
                icon = R.drawable.ic_rules_heart_s,
                backGround = R.drawable.ic_rules_todo_plus
            )
        )
    }

    fun fetchToTodayToDoList() {
        val tmpTodayToDoList = mutableListOf<Rule>(
            Rule(
                id = "sd;jnv;aovknkv;lsnm",
                isAllChecked = false,
                isTmpMember = false,
                ruleName = "화장실 청소",
                todayMembersWithTypeColor = listOf(
                    Homie(id = "sdasddvnds", typeColor = "GRAY", userName = "이준원"),
                    Homie(id = "sdksasdsdvnds", typeColor = "BLUE", userName = "이준원"),
                )
            ),
            Rule(
                id = "sd;jnvvsdnojkcz;lsnm",
                isAllChecked = false,
                isTmpMember = false,
                ruleName = "거실 청소기 돌리기",
                todayMembersWithTypeColor = listOf(
                    Homie(id = "sdasddvnds", typeColor = "PURPLE", userName = "이준원"),
                    Homie(id = "sdksasdsdvnds", typeColor = "BLUE", userName = "이준원"),
                    Homie(id = "sdksaasdadsdvnds", typeColor = "YELLOW", userName = "이준원"),
                    Homie(id = "sdksasdsdvnds", typeColor = "BLUE", userName = "이준원"),
                )
            ),
            Rule(
                id = "sd;jnvamfokassnm",
                isAllChecked = false,
                isTmpMember = false,
                ruleName = "냉장고 정리하기",
                todayMembersWithTypeColor = listOf(
                    Homie(id = "sdksadvasdvnds", typeColor = "PURPLE", userName = "이준원"),
                    Homie(id = "sdksadvasdvnds", typeColor = "BLUE", userName = "이준원"),
                    Homie(id = "sdksadvasdvnds", typeColor = "YELLOW", userName = "이준원"),
                )
            ),
            Rule(
                id = "sd;jnsad,;lsadokassnm",
                isAllChecked = true,
                isTmpMember = false,
                ruleName = "냉장고 정리하기",
                todayMembersWithTypeColor = listOf(
                    Homie(id = "sdksadvasdvnds", typeColor = "PURPLE", userName = "이준원"),
                )
            ),
            Rule(
                id = "sdssdmkvalmdasld,kassnm",
                isAllChecked = false,
                isTmpMember = true,
                ruleName = "냉장고 정리하기",
                todayMembersWithTypeColor = listOf(
                    Homie(id = "sdksadvasdvnds", typeColor = "PURPLE", userName = "이준원"),
                    Homie(id = "sdksadvasdvnds", typeColor = "GREEN", userName = "이준원"),
                )
            ),
            Rule(
                id = "sdssdmkvalmdasld,kassnm",
                isAllChecked = false,
                isTmpMember = true,
                ruleName = "냉장고 정리하기",
                todayMembersWithTypeColor = listOf(
                    Homie(id = "sdksadvasdvnds", typeColor = "PURPLE", userName = "이준원"),
                    Homie(id = "sdksadvasdvnds", typeColor = "GRAY", userName = "이준원"),
                )
            )
        )
        _todayTodoList.value = tmpTodayToDoList.map { data ->
            data.copy()
        }
    }

    fun fetchToMyTodayToDoList() {
        val tmpMyTodoList = mutableListOf(
            Rule(
                id = "sjodFdsfdsfdsflc",
                categoryIcon = "CLEAN",
                ruleName = "거실 청소기 돌리기",
                isChecked = false
            ),
            Rule(
                id = "sjklsdfdsfsmvklc",
                categoryIcon = "BEER",
                ruleName = "맥주 마시기",
                isChecked = true
            ),
            Rule(
                id = "sjodkajofsdfdsfdmaklc",
                categoryIcon = "LAUNDRY",
                ruleName = "세탁기 돌리기",
                isChecked = false
            ),
            Rule(
                id = "sjdsfsdfkmcsaklc",
                categoryIcon = "LIGHT",
                ruleName = "전구 갈기",
                isChecked = true
            ),
            Rule(
                id = "sjklfansdjkdsfdsdsmvklc",
                categoryIcon = "BEER",
                ruleName = "맥주 마시기",
                isChecked = true
            )
        )
        _myTodoList.value = tmpMyTodoList.map { data ->
            data.copy()
        }
    }

    fun setToDoViewType(toDoViewType: ToDoViewType) {
        _toDoViewType.value = toDoViewType
    }

    fun setSmileSelected(selected: Boolean) {
        _isSelectedCategorySmile.value = selected
    }

    fun initCategorySelected() {
        val tmpCategoryOfRuleList = requireNotNull(_categoryOfRuleList.value).map { data ->
            data.copy().apply { isSelected = false }
        }
        _categoryOfRuleList.value = tmpCategoryOfRuleList
    }

    fun setMyToDoCheckBoxSelected(position: Int) {
        val tmpMyToDoList = requireNotNull(_myTodoList.value).map { data ->
            data.copy()
        }
        val isSelected = tmpMyToDoList[position].isChecked
        tmpMyToDoList[position].isChecked = !isSelected
        _myTodoList.value = tmpMyToDoList.toList()
        // TODO 서버로 id, boolean
    }

    fun setCategorySelected(position: Int) {
        val tmpCategoryOfRuleList = requireNotNull(_categoryOfRuleList.value).map { data ->
            data.copy().apply { isSelected = false }
        }
        tmpCategoryOfRuleList[position].isSelected = true
        _categoryOfRuleList.value = tmpCategoryOfRuleList.toList()
    }

    companion object {
        private const val TAG = "viewmodel"
    }
}

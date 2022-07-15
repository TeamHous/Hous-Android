package com.hous.hous_aos.ui.rules

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hous.hous_aos.R
import com.hous.hous_aos.data.entity.rules.CategoryOfRuleResponse
import com.hous.hous_aos.data.entity.rules.ManagerData
import com.hous.hous_aos.data.entity.rules.TodayTodoResponse

class RulesViewModel : ViewModel() {
    private var _toDoViewType = MutableLiveData(ToDoViewType.TODAY_TO_DO)
    val toDoViewType: LiveData<ToDoViewType> get() = _toDoViewType

    private var _isSelectedCategorySmile = MutableLiveData<Boolean>(true)
    val isSelectedCategorySmile: LiveData<Boolean> get() = _isSelectedCategorySmile

    private var _categoryOfRuleList =
        MutableLiveData<MutableList<CategoryOfRuleResponse>>()
    val categoryOfRuleList get() = _categoryOfRuleList

    private var _todayTodoList =
        MutableLiveData<MutableList<TodayTodoResponse>>()
    val todayTodoList get() = _todayTodoList

    private fun fetchToCategoryOfRuleList() {
        /**  dummy data입니다만..??
         * */
        _categoryOfRuleList.value = mutableListOf(
            CategoryOfRuleResponse(
                name = "라면을",
                icon = R.drawable.ic_rules_heart_s,
                backGround = R.drawable.ic_rules_category_red_bg_m
            ),
            CategoryOfRuleResponse(
                name = "너무",
                icon = R.drawable.ic_rules_beer_s,
                backGround = R.drawable.ic_rules_category_yellow_bg_m
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

    init {
        fetchToTodayToDoList()
        fetchToCategoryOfRuleList()
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
        _categoryOfRuleList.value = tmpCategoryOfRuleList.toMutableList()
    }

    fun setCategorySelected(position: Int) {
        val tmpCategoryOfRuleList = requireNotNull(_categoryOfRuleList.value).map { data ->
            data.copy().apply { isSelected = false }
        }
        tmpCategoryOfRuleList[position].isSelected = true
        _categoryOfRuleList.value = tmpCategoryOfRuleList.toMutableList()
    }

    fun fetchToTodayToDoList() {
        _todayTodoList.value = mutableListOf<TodayTodoResponse>(
            TodayTodoResponse(
                id = "sd;jnv;aovknkv;lsnm",
                number = 0,
                isAllChecked = false,
                isTemporaryManager = false,
                ruleName = "화장실 청소",
                managerDataList = listOf<ManagerData>(),
                iconList = listOf<String>()
            ),
            TodayTodoResponse(
                id = "sd;jnvvsdnojkcz;lsnm",
                number = 4,
                isAllChecked = false,
                isTemporaryManager = false,
                ruleName = "거실 청소기 돌리기",
                managerDataList = listOf(
                    ManagerData(id = "sdklasdfdasfdslkvnds", name = "이준원"),
                    ManagerData(id = "sdkladsfdasfsd", name = "강원용"),
                    ManagerData(id = "adsfsfdasfsaf", name = "이영주"),
                    ManagerData(id = "s2asdfadsfxasslkvnds", name = "안드로이드킹")
                ),
                iconList = listOf("purple", "yellow", "red", "blue")
            ),
            TodayTodoResponse(
                id = "sd;jnvamfokassnm",
                number = 3,
                isAllChecked = false,
                isTemporaryManager = false,
                ruleName = "냉장고 정리하기",
                managerDataList = listOf(
                    ManagerData(id = "sdkldasfvdslkvnds", name = "최인영"),
                    ManagerData(id = "sdklmnsdafvsdasd", name = "이다영"),
                    ManagerData(id = "sdadsfsdasdsads", name = "최소현"),
                ),
                iconList = listOf("yellow", "red", "blue")
            ),
            TodayTodoResponse(
                id = "sd;jnsad,;lsadokassnm",
                number = 1,
                isAllChecked = true,
                isTemporaryManager = false,
                ruleName = "냉장고 정리하기",
                managerDataList = listOf(
                    ManagerData(id = "sdksadvasdvnds", name = "이준원"),
                ),
                iconList = listOf("purple")
            ),
            TodayTodoResponse(
                id = "sdssdmkvalmdasld,kassnm",
                number = 2,
                isAllChecked = false,
                isTemporaryManager = true,
                ruleName = "냉장고 정리하기",
                managerDataList = listOf(
                    ManagerData(id = "sdklmsdbasdfkvnds", name = "공혁준"),
                    ManagerData(id = "sdklsadsdasd", name = "김혜정"),
                ),
                iconList = listOf("green", "gray")
            )
        )
    }

    companion object {
        private const val TAG = "viewmodel"
    }
}
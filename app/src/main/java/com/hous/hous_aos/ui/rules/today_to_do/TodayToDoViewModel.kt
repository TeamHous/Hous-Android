package com.hous.hous_aos.ui.rules.today_to_do

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hous.hous_aos.data.entity.rules.ManagerData
import com.hous.hous_aos.data.entity.rules.TodayTodoResponse

class TodayToDoViewModel : ViewModel() {

    private var _todayTodoList =
        MutableLiveData<MutableList<TodayTodoResponse>>()
    val todayTodoList get() = _todayTodoList

    init {
        fetchToTodayToDoList()
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
}

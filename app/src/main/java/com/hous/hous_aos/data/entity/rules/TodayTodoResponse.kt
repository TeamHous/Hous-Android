package com.hous.hous_aos.data.entity.rules

data class TodayTodoResponse(
    val id: String,
    val number: Int,
    val isAllChecked: Boolean,
    val isTemporaryManager: Boolean,
    val ruleName: String,
    val managerDataList: List<ManagerData>?,
    val iconList: List<String>
)
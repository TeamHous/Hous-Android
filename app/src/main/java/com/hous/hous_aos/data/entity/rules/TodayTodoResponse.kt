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

/** null없고, []로 보내줌, managerDataList, iconList가 합쳐져서옴
/*    val number: Int? = managerDataList.size <= 이건 우리가 계산해줌
*/
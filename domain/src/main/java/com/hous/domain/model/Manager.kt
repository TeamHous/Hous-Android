package com.hous.domain.model

data class Manager(
    val managerHomie: HomieInfo = HomieInfo(
        userName = "담당자 없음",
        typeColor = "NULL"
    ),
    val dayDataList: List<DayDataInfo> = listOf(
        DayDataInfo("월", State.UNSELECT),
        DayDataInfo("화", State.UNSELECT),
        DayDataInfo("수", State.UNSELECT),
        DayDataInfo("목", State.UNSELECT),
        DayDataInfo("금", State.UNSELECT),
        DayDataInfo("토", State.UNSELECT),
        DayDataInfo("일", State.UNSELECT)
    )
)

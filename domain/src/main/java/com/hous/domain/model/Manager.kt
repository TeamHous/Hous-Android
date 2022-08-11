package com.hous.domain.model

data class Manager(
    val managerHomie: Homie = Homie(
        userName = "담당자 없음",
        typeColor = "NULL"
    ),
    val dayDataList: List<DayData> = listOf(
        DayData("월", State.UNSELECT),
        DayData("화", State.UNSELECT),
        DayData("수", State.UNSELECT),
        DayData("목", State.UNSELECT),
        DayData("금", State.UNSELECT),
        DayData("토", State.UNSELECT),
        DayData("일", State.UNSELECT)
    )
)

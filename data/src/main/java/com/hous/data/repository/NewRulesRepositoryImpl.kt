package com.hous.data.repository

import android.util.Log
import com.hous.data.entity.DayData
import com.hous.data.entity.Manager
import com.hous.data.entity.State
import com.hous.data.model.WrapperClass
import com.hous.data.model.request.Member
import com.hous.data.model.request.NewRulesRequest
import com.hous.data.model.response.NewRulesListResponse
import com.hous.data.source.remote.RemoteNewRulesDataSource
import javax.inject.Inject

class NewRulesRepositoryImpl @Inject constructor(
    private val remoteNewRulesDataSource: RemoteNewRulesDataSource
) : NewRulesRepository {
    override suspend fun addNewRule(
        ruleName: String,
        categoryId: String,
        notificationState: Boolean,
        checkBoxState: State,
        managerList: List<Manager>
    ): Result<WrapperClass<Any>> {
        val ruleMember = managerList.map {
            val dayList = dayToInt(it.dayDataList)
            Member(
                userId = it.managerHomie.id,
                day = dayList
            )
        }
        val newRulesRequest = NewRulesRequest(
            notificationState = notificationState,
            ruleName = ruleName,
            categoryId = categoryId,
            isKeyRules = checkBoxState == State.SELECT,
            ruleMembers = if (checkBoxState != State.SELECT) ruleMember else emptyList()
        )
        Log.d("NewRulesViewModel", "repository rules data: $newRulesRequest")
        return runCatching { remoteNewRulesDataSource.addNewRule(newRulesRequest) }
    }

    override suspend fun getNewRuleList(roomId: String): Result<WrapperClass<NewRulesListResponse>> =
        runCatching { remoteNewRulesDataSource.getNewRuleList(roomId) }

    private fun dayToInt(dayDataList: List<DayData>): List<Int> {
        val tempList = mutableListOf<Int>()
        for (index in 0 until 7) {
            if (dayDataList[index].dayState == State.SELECT) tempList.add(index)
        }
        return tempList
    }
}

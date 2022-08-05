package com.hous.data.repository

import android.util.Log
import com.hous.data.model.WrapperClass
import com.hous.data.model.request.Member
import com.hous.data.model.request.NewRulesRequest
import com.hous.data.model.response.NewRulesListResponse
import com.hous.data.source.remote.RemoteNewRulesDataSource
import com.hous.hous_aos.ui.newrules.DayData
import com.hous.hous_aos.ui.newrules.NewRulesUiState
import com.hous.hous_aos.ui.newrules.component.State
import javax.inject.Inject

class NewRulesRepositoryImpl @Inject constructor(
    private val remoteNewRulesDataSource: RemoteNewRulesDataSource
) : NewRulesRepository {
    override suspend fun addNewRule(newRulesUiState: NewRulesUiState): Result<WrapperClass<Any>> {
        val ruleMember = newRulesUiState.ManagerList.map {
            val dayList = dayToInt(it.dayDataList)
            Member(
                userId = it.managerHomie.id,
                day = dayList
            )
        }
        val newRulesRequest = NewRulesRequest(
            notificationState = newRulesUiState.notificationState,
            ruleName = newRulesUiState.ruleName,
            categoryId = newRulesUiState.categoryId,
            isKeyRules = newRulesUiState.checkBoxState == State.SELECT,
            ruleMembers = if (newRulesUiState.checkBoxState != State.SELECT) ruleMember else emptyList()
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

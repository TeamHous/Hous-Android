package com.hous.data.repository

import android.util.Log
import com.hous.data.model.request.Member
import com.hous.data.model.request.NewRulesRequest
import com.hous.data.source.remote.RemoteNewRulesDataSource
import com.hous.domain.model.DayDataInfo
import com.hous.domain.model.Manager
import com.hous.domain.model.NewRuleInfo
import com.hous.domain.model.State
import com.hous.domain.repository.NewRulesRepository
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
    ) {
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
        remoteNewRulesDataSource.addNewRule(newRulesRequest)
    }

    override suspend fun getNewRuleList(roomId: String): Result<NewRuleInfo> =
        runCatching {
            val response = remoteNewRulesDataSource.getNewRuleList(roomId)
            NewRuleInfo(
                response.data!!.ruleCategories.map { it.toCategoryInfo() },
                response.data.homies.map { it.toHomieInfo() }
            )
        }

    private fun dayToInt(dayDataList: List<DayDataInfo>): List<Int> {
        val tempList = mutableListOf<Int>()
        for (index in 0 until 7) {
            if (dayDataList[index].dayState == State.SELECT) tempList.add(index)
        }
        return tempList
    }
}

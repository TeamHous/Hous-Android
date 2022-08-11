package com.hous.data.source.remote

import com.hous.data.BuildConfig
import com.hous.data.api.RulesApi
import com.hous.data.model.request.MyToDoCheckRequest
import com.hous.data.model.request.TempManagerRequest
import com.hous.domain.model.RuleInfo
import com.hous.domain.model.TempManagerInfo
import com.hous.domain.model.rules.RulesTableInfo
import com.hous.domain.model.rules.RulesTodayInfo
import timber.log.Timber
import javax.inject.Inject

class RemoteRulesTodayDataSourceImpl @Inject constructor(
    private val rulesApi: RulesApi
) :
    RemoteRulesTodayDataSource {
    private val ROOM_ID = BuildConfig.ROOM_ID

    override suspend fun getTodayTodayInfoList(roomId: String): RulesTodayInfo? =
        runCatching {
            rulesApi.getTodayTodayInfoList(ROOM_ID)
        }.fold(
            { response ->
                return RulesTodayInfo(
                    response.data?.homeRuleCategories!!.map { it.toCategoryInfo() },
                    response.data.todayTodoRules.map { it.toRuleInfo() })
            },
            {
                Timber.e(it.message)
                return null
            }
        )

    override suspend fun getTempManagerInfoList(
        roomId: String,
        ruleId: String
    ): TempManagerInfo? =
        runCatching {
            rulesApi.getTempManagerInfoList(ROOM_ID, ruleId)
        }.fold(
            { response ->
                return TempManagerInfo(
                    response.data!!.id,
                    response.data.homies.map { it.toHomieInfo() }
                )
            },
            {
                Timber.e(it.message)
                return null
            }
        )


    override suspend fun putTempManagerInfoList(
        roomId: String,
        ruleId: String,
        tmpRuleMembers: TempManagerRequest
    ): Any? {
        return runCatching {
            rulesApi.putTempManagerInfoList(
                ROOM_ID,
                ruleId,
                tmpRuleMembers
            )
        }.fold(
            {}, {
                Timber.e(it.message)
                null
            }
        )
    }

    override suspend fun getMyToDoInfoList(roomId: String): List<RuleInfo>? {
        return runCatching { rulesApi.getMyTodoInfoList(ROOM_ID) }.fold(
            { response ->
                response.data?.map { it.toRuleInfo() }
            },
            {
                Timber.e(it.message)
                null
            }
        )
    }

    override suspend fun putMyToDoCheckLust(
        roomId: String,
        ruleId: String,
        isCheck: MyToDoCheckRequest
    ) {
        runCatching { rulesApi.putMyToDoCheckList(ROOM_ID, ruleId, isCheck) }.onFailure {
            Timber.e(it.message)
        }
    }

    override suspend fun getRuleTableInfoList(
        roomId: String,
        categoryId: String
    ): RulesTableInfo? =
        runCatching { rulesApi.getRuleTableInfoList(ROOM_ID, categoryId) }.fold(
            { response ->
                return RulesTableInfo(
                    response.data?.keyRules!!.map { it.toRuleInfo() },
                    response.data.rules.map { it.toRuleInfo() }
                )
            },
            {
                Timber.e(it.message)
                null
            }
        )
}

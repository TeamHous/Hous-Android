package com.hous.hous_aos.data.repository

import com.hous.hous_aos.data.model.WrapperClass
import com.hous.hous_aos.data.source.remote.RemoteNewRulesDataSourceImpl
import com.hous.hous_aos.ui.newrules.NewRulesUiState
import javax.inject.Inject

class NewRulesRepositoryImpl @Inject constructor(
    private val remoteNewRulesDataSourceImpl: RemoteNewRulesDataSourceImpl
) : NewRulesRepository {
    override suspend fun addNewRule(newRulesUiState: NewRulesUiState): Result<WrapperClass<Any>> {
        TODO("Not yet implemented")
    }
}

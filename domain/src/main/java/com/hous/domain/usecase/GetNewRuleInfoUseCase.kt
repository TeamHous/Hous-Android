package com.hous.domain.usecase

import com.hous.domain.model.NewRuleInfo
import com.hous.domain.repository.NewRulesRepository
import javax.inject.Inject

class GetNewRuleInfoUseCase @Inject constructor(
    private val newRulesRepository: NewRulesRepository
) {
    suspend operator fun invoke(roomId: String): Result<NewRuleInfo> =
        newRulesRepository.getNewRuleList(roomId)
}

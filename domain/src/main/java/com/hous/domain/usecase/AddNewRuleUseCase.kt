package com.hous.domain.usecase

import com.hous.domain.model.Manager
import com.hous.domain.model.State
import com.hous.domain.repository.NewRulesRepository
import javax.inject.Inject

class AddNewRuleUseCase @Inject constructor(
    private val newRulesRepository: NewRulesRepository
) {
    suspend operator fun invoke(
        ruleName: String,
        categoryId: String,
        notificationState: Boolean,
        checkBoxState: State,
        managerList: List<Manager>
    ) = newRulesRepository.addNewRule(
        ruleName,
        categoryId,
        notificationState,
        checkBoxState,
        managerList
    )
}

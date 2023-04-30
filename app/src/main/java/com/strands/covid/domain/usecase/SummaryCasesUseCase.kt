package com.strands.covid.domain.usecase

import com.strands.covid.domain.model.general.ResultCall
import com.strands.covid.domain.repository.CovidCaseRepository

class SummaryCasesUseCase(private val repository: CovidCaseRepository) {
    suspend fun invoke(): ResultCall<String> {
        return repository.getSummaryCases()
    }
}

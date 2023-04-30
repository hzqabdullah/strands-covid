package com.strands.covid.domain.usecase

import com.strands.covid.domain.model.general.ResultCall
import com.strands.covid.domain.repository.CovidCaseRepository

class ConfirmedCasesByCountryUseCase(private val repository: CovidCaseRepository) {
    suspend fun invoke(countrySlug: String): ResultCall<String> {
        return repository.getConfirmedByCountry(countrySlug)
    }
}

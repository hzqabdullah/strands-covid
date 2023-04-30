package com.strands.covid.domain.repository

import com.strands.covid.base.networkRequest
import com.strands.covid.constant.BaseUrl.BY_COUNTRY_API
import com.strands.covid.constant.BaseUrl.SUMMARY_CASES_API
import com.strands.covid.domain.model.general.ResultCall

interface CovidCaseRepository {
    suspend fun getSummaryCases(): ResultCall<String>
    suspend fun getConfirmedByCountry(countrySlug: String): ResultCall<String>
}

class CovidCaseRepositoryImpl : CovidCaseRepository {
    override suspend fun getSummaryCases(): ResultCall<String> {
        return networkRequest(SUMMARY_CASES_API)
    }

    override suspend fun getConfirmedByCountry(countrySlug: String): ResultCall<String> {
        return networkRequest("$BY_COUNTRY_API/$countrySlug/status/confirmed")
    }
}

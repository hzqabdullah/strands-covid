package com.strands.covid.domain.model.generator

import com.strands.covid.constant.BaseUrl.COUNTRY_FLAG_API

object CountryFlagGenerator {
    fun getFlagImagePath(countryCode: String): String {
        return "${COUNTRY_FLAG_API}/SHINY/64/${countryCode}.png"
    }
}

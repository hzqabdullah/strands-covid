package com.strands.covid.domain.model.response

import com.google.gson.annotations.SerializedName
import com.strands.covid.domain.model.general.AffectedCountry

data class SummaryCases(
    @SerializedName("Countries")
    val countries: List<AffectedCountry> = emptyList()
)

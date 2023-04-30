package com.strands.covid.domain.model.general

import com.google.gson.annotations.SerializedName
import com.strands.covid.util.emptyBigDecimal
import com.strands.covid.util.emptyString
import java.io.Serializable
import java.math.BigDecimal
import java.math.BigDecimal.ONE

data class AffectedCountry(
    @SerializedName("ID")
    val countryId: String = emptyString(),
    @SerializedName("Country")
    val countryName: String = emptyString(),
    @SerializedName("CountryCode")
    val countryCode: String = emptyString(),
    @SerializedName("Slug")
    val countrySlug: String = emptyString(),
    @SerializedName("NewConfirmed")
    val newConfirmedCase: BigDecimal = emptyBigDecimal(),
    @SerializedName("TotalConfirmed")
    val totalConfirmedCase: BigDecimal = emptyBigDecimal(),
    @SerializedName("NewDeaths")
    val newDeathCase: BigDecimal = emptyBigDecimal(),
    @SerializedName("TotalDeaths")
    val totalDeathCase: BigDecimal = emptyBigDecimal(),
    @SerializedName("NewRecovered")
    val newRecoveredCase: BigDecimal = emptyBigDecimal(),
    @SerializedName("TotalRecovered")
    val totalRecoveredCase: BigDecimal = emptyBigDecimal(),
    @SerializedName("Cases")
    val totalCases: BigDecimal = emptyBigDecimal(),
    @SerializedName("Date")
    val date: String = emptyString()
) : Serializable {
    fun getActiveCase(): String {
        if (newConfirmedCase == emptyBigDecimal()) return "Active case: None"
        if (newConfirmedCase > ONE) return "Active cases: $newConfirmedCase"
        return "Active case: $newConfirmedCase"
    }

    fun getDeathCase(): String {
        if (totalDeathCase == emptyBigDecimal()) return "Death case: None"
        if (totalDeathCase > ONE) return "Death cases: $totalDeathCase"
        return "Death case: $totalDeathCase"
    }
}

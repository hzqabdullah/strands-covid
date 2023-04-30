package com.strands.covid.enums

import com.strands.covid.domain.model.general.AffectedCountry
import com.strands.covid.domain.model.generator.CaseProportionGenerator

enum class CountrySortByType {
    ALL {
        override fun getFilteredCountryList(
            countries: List<AffectedCountry>
        ): List<AffectedCountry> = countries
    },
    ACTIVE {
        override fun getFilteredCountryList(
            countries: List<AffectedCountry>
        ): List<AffectedCountry> = countries.sortedByDescending { it.newConfirmedCase }
    },
    DEATH {
        override fun getFilteredCountryList(
            countries: List<AffectedCountry>
        ): List<AffectedCountry> = countries.sortedByDescending { it.totalDeathCase }
    },
    ACTIVE_PER_HAB {
        override fun getFilteredCountryList(
            countries: List<AffectedCountry>
        ): List<AffectedCountry> = countries.sortedByDescending {
            CaseProportionGenerator.getCasePerHab(it.newConfirmedCase)
        }
    },
    DEATH_PER_HAB {
        override fun getFilteredCountryList(
            countries: List<AffectedCountry>
        ): List<AffectedCountry> = countries.sortedByDescending {
            CaseProportionGenerator.getCasePerHab(it.totalDeathCase)
        }
    };

    abstract fun getFilteredCountryList(countries: List<AffectedCountry>): List<AffectedCountry>
}

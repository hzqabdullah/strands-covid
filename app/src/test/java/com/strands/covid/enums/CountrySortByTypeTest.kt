package com.strands.covid.enums

import com.google.gson.Gson
import com.strands.covid.domain.model.general.AffectedCountry
import com.strands.covid.domain.model.response.SummaryCases
import com.strands.covid.enums.CountrySortByType.ACTIVE
import com.strands.covid.enums.CountrySortByType.ACTIVE_PER_HAB
import com.strands.covid.enums.CountrySortByType.ALL
import com.strands.covid.enums.CountrySortByType.DEATH
import com.strands.covid.enums.CountrySortByType.DEATH_PER_HAB
import com.strands.covid.util.emptyString
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import java.math.BigDecimal

class CountrySortByTypeTest {
    private var jsonString = emptyString()
    private var countries = emptyList<AffectedCountry>()

    @Before
    fun setUp() {
        jsonString = """
        {
            "Countries": [
                {
                    "ID": "c2695f63-15e1",
                    "Country": "Malaysia",
                    "CountryCode": "MY",
                    "Slug": "malaysia",
                    "NewConfirmed": 279,
                    "TotalConfirmed": 5044718,
                    "NewDeaths": 0,
                    "TotalDeaths": 36967,
                    "NewRecovered": 0,
                    "TotalRecovered": 0,
                    "Date": "2023-04-30T06:49:41.244Z"
                },
                {
                    "ID": "a82edcb2-47f5",
                    "Country": "Albania",
                    "CountryCode": "AL",
                    "Slug": "albania",
                    "NewConfirmed": 14,
                    "TotalConfirmed": 334457,
                    "NewDeaths": 0,
                    "TotalDeaths": 3598,
                    "NewRecovered": 0,
                    "TotalRecovered": 0,
                    "Date": "2023-04-30T06:49:41.244Z"
                }
            ]
        }
        """.trimIndent()

        countries = listOf(
            AffectedCountry(
                countryId = "c2695f63-15e1",
                countryName = "Malaysia",
                countryCode = "MY",
                countrySlug = "malaysia",
                newConfirmedCase = BigDecimal(279),
                totalConfirmedCase = BigDecimal(5044718),
                newDeathCase = BigDecimal(0),
                totalDeathCase = BigDecimal(36967),
                newRecoveredCase = BigDecimal(0),
                totalRecoveredCase = BigDecimal(0),
                date = "2023-04-30T06:49:41.244Z"
            ),
            AffectedCountry(
                countryId = "a82edcb2-47f5",
                countryName = "Albania",
                countryCode = "AL",
                countrySlug = "albania",
                newConfirmedCase = BigDecimal(14),
                totalConfirmedCase = BigDecimal(334457),
                newDeathCase = BigDecimal(0),
                totalDeathCase = BigDecimal(3598),
                newRecoveredCase = BigDecimal(0),
                totalRecoveredCase = BigDecimal(0),
                date = "2023-04-30T06:49:41.244Z"
            )
        )
    }

    @Test
    fun `should get country list sort by default`() {
        val model = Gson().fromJson(jsonString, SummaryCases::class.java)
        val sortCountryByType = ALL
        val actual = sortCountryByType.getFilteredCountryList(model.countries)
        val expected = sortCountryByType.getFilteredCountryList(countries)

        assertNotNull(actual)
        assertNotNull(actual)
        assertEquals(actual.size, 2)
        assertEquals(actual, expected)
        assertEquals(actual.first().countryId, expected.first().countryId)
        assertEquals(actual.first().countryName, expected.first().countryName)
        assertEquals(actual.first().countryCode, expected.first().countryCode)
        assertEquals(actual.first().countrySlug, expected.first().countrySlug)
        assertEquals(actual.first().newConfirmedCase, expected.first().newConfirmedCase)
        assertEquals(actual.first().totalConfirmedCase, expected.first().totalConfirmedCase)
        assertEquals(actual.first().newDeathCase, expected.first().newDeathCase)
        assertEquals(actual.first().totalDeathCase, expected.first().totalDeathCase)
        assertEquals(actual.first().newRecoveredCase, expected.first().newRecoveredCase)
        assertEquals(actual.first().totalRecoveredCase, expected.first().totalRecoveredCase)
        assertEquals(actual.first().date, expected.first().date)
    }

    @Test
    fun `should get country list sort by active case`() {
        val model = Gson().fromJson(jsonString, SummaryCases::class.java)
        val sortCountryByType = ACTIVE
        val actual = sortCountryByType.getFilteredCountryList(model.countries)
        val expected = sortCountryByType.getFilteredCountryList(countries)

        assertNotNull(actual)
        assertNotNull(actual)
        assertEquals(actual.size, 2)
        assertEquals(actual, expected)
        assertEquals(actual.first().countryId, expected.first().countryId)
        assertEquals(actual.first().countryName, expected.first().countryName)
        assertEquals(actual.first().countryCode, expected.first().countryCode)
        assertEquals(actual.first().countrySlug, expected.first().countrySlug)
        assertEquals(actual.first().newConfirmedCase, expected.first().newConfirmedCase)
        assertEquals(actual.first().totalConfirmedCase, expected.first().totalConfirmedCase)
        assertEquals(actual.first().newDeathCase, expected.first().newDeathCase)
        assertEquals(actual.first().totalDeathCase, expected.first().totalDeathCase)
        assertEquals(actual.first().newRecoveredCase, expected.first().newRecoveredCase)
        assertEquals(actual.first().totalRecoveredCase, expected.first().totalRecoveredCase)
        assertEquals(actual.first().date, expected.first().date)
    }

    @Test
    fun `should get country list sort by death case`() {
        val model = Gson().fromJson(jsonString, SummaryCases::class.java)
        val sortCountryByType = DEATH
        val actual = sortCountryByType.getFilteredCountryList(model.countries)
        val expected = sortCountryByType.getFilteredCountryList(countries)

        assertNotNull(actual)
        assertNotNull(actual)
        assertEquals(actual.size, 2)
        assertEquals(actual, expected)
        assertEquals(actual.first().countryId, expected.first().countryId)
        assertEquals(actual.first().countryName, expected.first().countryName)
        assertEquals(actual.first().countryCode, expected.first().countryCode)
        assertEquals(actual.first().countrySlug, expected.first().countrySlug)
        assertEquals(actual.first().newConfirmedCase, expected.first().newConfirmedCase)
        assertEquals(actual.first().totalConfirmedCase, expected.first().totalConfirmedCase)
        assertEquals(actual.first().newDeathCase, expected.first().newDeathCase)
        assertEquals(actual.first().totalDeathCase, expected.first().totalDeathCase)
        assertEquals(actual.first().newRecoveredCase, expected.first().newRecoveredCase)
        assertEquals(actual.first().totalRecoveredCase, expected.first().totalRecoveredCase)
        assertEquals(actual.first().date, expected.first().date)
    }

    @Test
    fun `should get country list sort by active case per hab`() {
        val model = Gson().fromJson(jsonString, SummaryCases::class.java)
        val sortCountryByType = ACTIVE_PER_HAB
        val actual = sortCountryByType.getFilteredCountryList(model.countries)
        val expected = sortCountryByType.getFilteredCountryList(countries)

        assertNotNull(actual)
        assertNotNull(actual)
        assertEquals(actual.size, 2)
        assertEquals(actual, expected)
        assertEquals(actual.first().countryId, expected.first().countryId)
        assertEquals(actual.first().countryName, expected.first().countryName)
        assertEquals(actual.first().countryCode, expected.first().countryCode)
        assertEquals(actual.first().countrySlug, expected.first().countrySlug)
        assertEquals(actual.first().newConfirmedCase, expected.first().newConfirmedCase)
        assertEquals(actual.first().totalConfirmedCase, expected.first().totalConfirmedCase)
        assertEquals(actual.first().newDeathCase, expected.first().newDeathCase)
        assertEquals(actual.first().totalDeathCase, expected.first().totalDeathCase)
        assertEquals(actual.first().newRecoveredCase, expected.first().newRecoveredCase)
        assertEquals(actual.first().totalRecoveredCase, expected.first().totalRecoveredCase)
        assertEquals(actual.first().date, expected.first().date)
    }

    @Test
    fun `should get country list sort by death case per hab`() {
        val model = Gson().fromJson(jsonString, SummaryCases::class.java)
        val sortCountryByType = DEATH_PER_HAB
        val actual = sortCountryByType.getFilteredCountryList(model.countries)
        val expected = sortCountryByType.getFilteredCountryList(countries)

        assertNotNull(actual)
        assertNotNull(actual)
        assertEquals(actual.size, 2)
        assertEquals(actual, expected)
        assertEquals(actual.first().countryId, expected.first().countryId)
        assertEquals(actual.first().countryName, expected.first().countryName)
        assertEquals(actual.first().countryCode, expected.first().countryCode)
        assertEquals(actual.first().countrySlug, expected.first().countrySlug)
        assertEquals(actual.first().newConfirmedCase, expected.first().newConfirmedCase)
        assertEquals(actual.first().totalConfirmedCase, expected.first().totalConfirmedCase)
        assertEquals(actual.first().newDeathCase, expected.first().newDeathCase)
        assertEquals(actual.first().totalDeathCase, expected.first().totalDeathCase)
        assertEquals(actual.first().newRecoveredCase, expected.first().newRecoveredCase)
        assertEquals(actual.first().totalRecoveredCase, expected.first().totalRecoveredCase)
        assertEquals(actual.first().date, expected.first().date)
    }
}


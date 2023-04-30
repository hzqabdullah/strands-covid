package com.strands.covid.response

import com.google.gson.Gson
import com.strands.covid.domain.model.general.AffectedCountry
import com.strands.covid.domain.model.response.SummaryCases
import com.strands.covid.util.emptyString
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import java.math.BigDecimal

class SummaryCasesTest {
    private var jsonString = emptyString()

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
    }

    @Test
    fun `should be map model from json string`() {
        val model = Gson().fromJson(jsonString, SummaryCases::class.java)
        val countries = listOf(
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
        assertNotNull(model)
        assertNotNull(model.countries)
        assertEquals(model.countries.size, 2)
        assertEquals(countries.first().countryId, model.countries.first().countryId)
        assertEquals(countries.first().countryName, model.countries.first().countryName)
        assertEquals(countries.first().countryCode, model.countries.first().countryCode)
        assertEquals(countries.first().countrySlug, model.countries.first().countrySlug)
        assertEquals(countries.first().newConfirmedCase, model.countries.first().newConfirmedCase)
        assertEquals(
            countries.first().totalConfirmedCase,
            model.countries.first().totalConfirmedCase
        )
        assertEquals(countries.first().newDeathCase, model.countries.first().newDeathCase)
        assertEquals(countries.first().totalDeathCase, model.countries.first().totalDeathCase)
        assertEquals(countries.first().newRecoveredCase, model.countries.first().newRecoveredCase)
        assertEquals(
            countries.first().totalRecoveredCase,
            model.countries.first().totalRecoveredCase
        )
        assertEquals(countries.first().date, model.countries.first().date)
    }

    @Test
    fun `should be map data from model`() {
        val model = listOf(
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
        assertNotNull(model)
        assertEquals(model.size, 2)
        assertEquals(model.first().countryId, "c2695f63-15e1")
        assertEquals(model.first().countryName, "Malaysia")
        assertEquals(model.first().countryCode, "MY")
        assertEquals(model.first().countrySlug, "malaysia")
        assertEquals(model.first().newConfirmedCase, BigDecimal(279))
        assertEquals(model.first().totalConfirmedCase, BigDecimal(5044718))
        assertEquals(model.first().newDeathCase, BigDecimal(0))
        assertEquals(model.first().totalDeathCase, BigDecimal(36967))
        assertEquals(model.first().newRecoveredCase, BigDecimal(0))
        assertEquals(model.first().totalRecoveredCase, BigDecimal(0))
        assertEquals(model.first().date, "2023-04-30T06:49:41.244Z")
    }
}

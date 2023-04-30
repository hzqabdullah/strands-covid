package com.strands.covid.model

import com.google.gson.Gson
import com.strands.covid.domain.model.general.AffectedCountry
import com.strands.covid.util.emptyString
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import java.math.BigDecimal

class AffectedCountryTest {
    private var jsonString = emptyString()

    @Before
    fun setUp() {
        jsonString = """
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
        }
        """.trimIndent()
    }

    @Test
    fun `should be map model from json string`() {
        val model = Gson().fromJson(jsonString, AffectedCountry::class.java)
        val affectedCountry = AffectedCountry(
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
        )
        assertNotNull(model)
        assertEquals(affectedCountry.countryId, model.countryId)
        assertEquals(affectedCountry.countryName, model.countryName)
        assertEquals(affectedCountry.countryCode, model.countryCode)
        assertEquals(affectedCountry.countrySlug, model.countrySlug)
        assertEquals(affectedCountry.newConfirmedCase, model.newConfirmedCase)
        assertEquals(affectedCountry.totalConfirmedCase, model.totalConfirmedCase)
        assertEquals(affectedCountry.newDeathCase, model.newDeathCase)
        assertEquals(affectedCountry.totalDeathCase, model.totalDeathCase)
        assertEquals(affectedCountry.newRecoveredCase, model.newRecoveredCase)
        assertEquals(affectedCountry.totalRecoveredCase, model.totalRecoveredCase)
        assertEquals(affectedCountry.date, model.date)
    }

    @Test
    fun `should be map data from model`() {
        val model = AffectedCountry(
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
        )
        assertNotNull(model)
        assertEquals(model.countryId, "c2695f63-15e1")
        assertEquals(model.countryName, "Malaysia")
        assertEquals(model.countryCode, "MY")
        assertEquals(model.countrySlug, "malaysia")
        assertEquals(model.newConfirmedCase, BigDecimal(279))
        assertEquals(model.totalConfirmedCase, BigDecimal(5044718))
        assertEquals(model.newDeathCase, BigDecimal(0))
        assertEquals(model.totalDeathCase, BigDecimal(36967))
        assertEquals(model.newRecoveredCase, BigDecimal(0))
        assertEquals(model.totalRecoveredCase, BigDecimal(0))
        assertEquals(model.date, "2023-04-30T06:49:41.244Z")
    }
}

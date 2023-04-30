package com.strands.covid.generator

import com.strands.covid.domain.model.generator.CountryFlagGenerator
import org.junit.Assert.assertEquals
import org.junit.Test

class CountryFlagGeneratorTest {

    @Test
    fun `should return string`() {
        val expected = "https://www.countryflagicons.com/SHINY/64/MY.png"
        val countryCode = "MY"
        val actual = CountryFlagGenerator.getFlagImagePath(countryCode)
        assertEquals(expected, actual)
    }
}

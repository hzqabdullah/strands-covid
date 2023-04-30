package com.strands.covid.generator

import com.strands.covid.domain.model.generator.DateParserGenerator
import org.junit.Assert.assertEquals
import org.junit.Test

class DateParserGenerator {

    @Test
    fun `should return integer`() {
        val expected = 3
        val startDate = "2020-01-31"
        val endDate = "2023-03-09"
        val actual = DateParserGenerator.getYearsBetween(startDate, endDate)
        assertEquals(expected, actual)
    }
}

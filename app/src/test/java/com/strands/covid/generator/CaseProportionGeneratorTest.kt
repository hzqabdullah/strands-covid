package com.strands.covid.generator

import com.strands.covid.domain.model.generator.CaseProportionGenerator
import org.junit.Assert.assertEquals
import org.junit.Test
import java.math.BigDecimal

class CaseProportionGeneratorTest {

    @Test
    fun `should return double`() {
        val expected = 0.9
        val data = BigDecimal(90000)
        val actual = CaseProportionGenerator.getCasePerHab(data)
        assertEquals(expected, actual, 0.3)
    }

    @Test
    fun `should return string`() {
        val expected = "0.00001"
        val data = BigDecimal(1)
        val actual = CaseProportionGenerator.getCasePerHabInString(data)
        assertEquals(expected, actual)
    }
}

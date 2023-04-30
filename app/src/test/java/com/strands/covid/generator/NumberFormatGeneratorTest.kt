package com.strands.covid.generator

import com.strands.covid.domain.model.generator.NumberFormatGenerator.formatNumberWithComma
import org.junit.Assert.assertEquals
import org.junit.Test
import java.math.BigDecimal

class NumberFormatGeneratorTest {

    @Test
    fun `should convert from big decimal to string`() {
        val expected = "1,000,000"
        val actual = BigDecimal(1000000).formatNumberWithComma()
        assertEquals(expected, actual)
    }
}

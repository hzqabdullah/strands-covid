package com.strands.covid.generator

import com.strands.covid.domain.model.generator.DateFormatGenerator
import com.strands.covid.domain.model.generator.DateFormatGenerator.DATE_TIME
import com.strands.covid.domain.model.generator.DateFormatGenerator.ISO8601_MILLIS
import org.junit.Assert.assertEquals
import org.junit.Test

class DateFormatGeneratorTest {

    @Test
    fun `should convert from current format date to expected format date`() {
        val expected = "30 Apr 2023, 06:09:41am"
        val actual = DateFormatGenerator.convertDate(
            date = "2023-04-30T06:09:41.244Z",
            currentFormat = ISO8601_MILLIS,
            expectedFormat = DATE_TIME
        )
        assertEquals(expected, actual)
    }
}

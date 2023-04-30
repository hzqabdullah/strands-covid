package com.strands.covid.domain.model.generator

import android.annotation.SuppressLint
import java.time.LocalDate

object DateParserGenerator {
    @SuppressLint("NewApi")
    fun getYearsBetween(startDateInString: String, endDateInString: String): Int {
        val startDate = LocalDate.parse(startDateInString)
        val endDate = LocalDate.parse(endDateInString)
        val period = startDate.until(endDate)
        return period.years
    }
}

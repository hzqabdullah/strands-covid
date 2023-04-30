package com.strands.covid.domain.model.generator

import com.strands.covid.util.emptyString
import com.strands.covid.util.silence
import java.text.SimpleDateFormat
import java.util.Locale


object DateFormatGenerator {
    const val ISO8601 = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    const val ISO8601_MILLIS = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    const val DATE_TIME = "dd MMM YYYY, hh:mm:ssaa"
    const val DATE = "YYYY-MM-dd"
    const val YEAR = "YYYY"

    fun convertDate(date: String, currentFormat: String, expectedFormat: String): String {
        return convertDateFormat(
            date = date,
            currentFormat = currentFormat,
            expectedFormat = expectedFormat
        )
    }

    private fun convertDateFormat(
        date: String,
        currentFormat: String,
        expectedFormat: String
    ): String {
        val simpleDateFormat = SimpleDateFormat(currentFormat, Locale.getDefault())
        var formattedDate = emptyString()
        silence {
            val expectedSimpleDateFormat = SimpleDateFormat(expectedFormat, Locale.getDefault())
            val convertedDate = simpleDateFormat.parse(date)
            convertedDate?.let { formattedDate = expectedSimpleDateFormat.format(it) }
        }
        return formattedDate
    }
}

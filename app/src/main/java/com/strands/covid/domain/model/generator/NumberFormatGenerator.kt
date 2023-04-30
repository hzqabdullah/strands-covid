package com.strands.covid.domain.model.generator

import java.math.BigDecimal
import java.text.DecimalFormat

object NumberFormatGenerator {
    private val numberWithComma = DecimalFormat("#,##0")

    fun BigDecimal.formatNumberWithComma(): String {
        return numberWithComma.format(this)
    }
}

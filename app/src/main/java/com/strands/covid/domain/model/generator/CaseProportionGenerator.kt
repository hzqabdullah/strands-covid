package com.strands.covid.domain.model.generator

import java.math.BigDecimal

object CaseProportionGenerator {
    fun getCasePerHab(case: BigDecimal): Double {
        return case.divide(BigDecimal(100000)).toDouble()
    }

    fun getCasePerHabInString(case: BigDecimal): String {
        return case.divide(BigDecimal(100000)).toPlainString()
    }
}

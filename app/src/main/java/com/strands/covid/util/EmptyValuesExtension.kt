package com.strands.covid.util

import com.strands.covid.constant.EmptyValues
import java.math.BigDecimal

fun emptyString(): String = EmptyValues.STRING

fun emptyInt(): Int = EmptyValues.INT

fun Float?.orEmpty(): Float = this ?: EmptyValues.FLOAT

fun emptyBigDecimal(): BigDecimal = BigDecimal.ZERO

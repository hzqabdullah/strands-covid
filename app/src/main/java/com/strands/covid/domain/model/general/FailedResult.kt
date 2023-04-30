package com.strands.covid.domain.model.general

import com.strands.covid.util.emptyString

data class FailedResult(
    val title: String = emptyString(),
    val description: String = emptyString()
)

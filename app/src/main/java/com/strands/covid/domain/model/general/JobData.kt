package com.strands.covid.domain.model.general

import kotlinx.coroutines.Job

data class JobData(
    val identifier: Long,
    val isReloadWhenFail: Boolean,
    val loadData: suspend (identifier: Long) -> Unit = {},
    var isCancel: Boolean = false,
    var isActive: Boolean = true,
    var job: Job? = null
)

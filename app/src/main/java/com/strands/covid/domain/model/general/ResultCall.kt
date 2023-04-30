package com.strands.covid.domain.model.general

sealed class ResultCall<out T> {
    data class Success(val data: String) : ResultCall<String>()
    data class Failed(val failedResult: FailedResult) : ResultCall<Nothing>()
}

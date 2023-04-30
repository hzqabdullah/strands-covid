package com.strands.covid.network

import com.strands.covid.base.networkRequest
import com.strands.covid.constant.BaseUrl.SUMMARY_CASES_API
import com.strands.covid.domain.model.general.FailedResult
import com.strands.covid.domain.model.general.ResultCall
import io.mockk.clearAllMocks
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class NetworkRequestTest {
    private val mockEndPoint = "https://www.abc.com"
    private val failedResult = FailedResult(
        title = "Error Title",
        description = "Error Description"
    )
    private val itemErrorMessage = ResultCall.Failed(failedResult)

    @Before
    fun setUp() {
        clearAllMocks()
    }

    @Test
    fun `should get data from network request`() = runBlocking {
        when (val result = networkRequest(SUMMARY_CASES_API)) {
            is ResultCall.Failed -> assertEquals(result, itemErrorMessage)
            is ResultCall.Success -> assertNotNull(result.data)
        }
    }

    @Test
    fun `should get error from network request`() = runBlocking {
        when (val result = networkRequest(mockEndPoint)) {
            is ResultCall.Failed -> assertEquals(result, itemErrorMessage)
            is ResultCall.Success -> assertNotNull(result.data)
        }
    }
}

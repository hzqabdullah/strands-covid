package com.strands.covid.base

import com.strands.covid.domain.model.general.FailedResult
import com.strands.covid.domain.model.general.ResultCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

suspend fun networkRequest(baseUrl: String): ResultCall<String> {
    var httpURLConnection: HttpURLConnection? = null
    val url = URL(baseUrl)

    return withContext(Dispatchers.IO) {
        try {
            httpURLConnection = url.openConnection() as HttpURLConnection
            if (httpURLConnection?.responseCode != 200) return@withContext ResultCall.Failed(
                FailedResult(
                    title = "Failed",
                    description = "Please try again..."
                )
            )

            val bufferedReader = BufferedReader(InputStreamReader(httpURLConnection?.inputStream))
            val jsonStringHolder: StringBuilder = StringBuilder()
            val readLine = bufferedReader.readLine() ?: return@withContext ResultCall.Failed(
                FailedResult(
                    title = "Failed",
                    description = "Please try again..."
                )
            )
            jsonStringHolder.append(readLine)
            return@withContext ResultCall.Success(jsonStringHolder.toString())

        } catch (ioexception: IOException) {
            return@withContext ResultCall.Failed(
                FailedResult(
                    title = "Failed",
                    description = ioexception.message.toString()
                )
            )
        } finally {
            httpURLConnection?.disconnect()
        }
    }
}

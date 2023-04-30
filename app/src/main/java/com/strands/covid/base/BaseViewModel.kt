package com.strands.covid.base

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import com.strands.covid.domain.model.general.JobData
import com.strands.covid.util.silence
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

open class BaseViewModel : ViewModel(), CoroutineScope {

    private val job = Job()
    private val jobDataList = mutableListOf<JobData>()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        jobDataList.clear()
        job.cancel()
    }

    private fun getJobDataIndex(jobData: JobData) = jobDataList.indexOf(jobData)
    private fun generateIdentifier(): Long = System.currentTimeMillis()

    private fun JobData.launchJobData() {
        val currentJob = launch {
            try {
                loadData.invoke(identifier)
            } catch (exception: CancellationException) {
                cancelJob()
            }
        }
        job = currentJob
        jobDataList.add(this)
        currentJob.invokeOnCompletion {
            silence {
                val index = getJobDataIndex(this)
                jobDataList[index].isActive = false
            }
        }
    }

    private fun JobData.cancelJob() {
        silence {
            val index = getJobDataIndex(this)
            jobDataList[index].isCancel = true
        }
    }

    protected fun launchJob(
        isNeedToReloadWhenFailed: Boolean = true, block: suspend (identifier: Long) -> Unit
    ) {
        JobData(
            identifier = generateIdentifier(),
            isReloadWhenFail = isNeedToReloadWhenFailed,
            loadData = block
        ).launchJobData()
    }

    fun apiDelay(timeInMillis: Long) = launch {
        delay(timeInMillis)
    }
}

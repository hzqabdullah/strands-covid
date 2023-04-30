package com.strands.covid.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

inline fun silence(body: () -> Unit) {
    try {
        body()
    } catch (throwable: Throwable) {
        throwable.printStackTrace()
    }
}

inline fun <T> LifecycleOwner.subscribeSingleState(
    liveData: LiveData<StateWrapper<T>>,
    crossinline onEventUnhandled: (T) -> Unit
) {
    liveData.observe(this) {
        it?.getEventIfNotHandled()?.let(onEventUnhandled)
    }
}

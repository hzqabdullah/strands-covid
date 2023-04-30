package com.strands.covid.util

open class StateWrapper<out T>(private val content: T) {

    private var hasBeenHandled: Boolean = false

    fun getEventIfNotHandled(): T? {
        return when (hasBeenHandled) {
            true -> null
            false -> {
                hasBeenHandled
                content
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is StateWrapper<*>) return false
        if (content != other.content) return false
        if (hasBeenHandled != other.hasBeenHandled) return false
        return true
    }
}

package com.strands.covid.enums

import android.graphics.Color

enum class QuarterOfYear {
    ONE {
        override fun getQuarterDate() = "03-31"
        override fun getQuarterLabel() = "Quarter 1"
        override fun getQuarterColor() = Color.LTGRAY
    },
    TWO {
        override fun getQuarterDate() = "06-30"
        override fun getQuarterLabel() = "Quarter 2"
        override fun getQuarterColor() = Color.GRAY
    },
    THREE {
        override fun getQuarterDate() = "09-30"
        override fun getQuarterLabel() = "Quarter 3"
        override fun getQuarterColor() = Color.DKGRAY
    },
    FOUR {
        override fun getQuarterDate() = "12-30"
        override fun getQuarterLabel() = "Quarter 4"
        override fun getQuarterColor() = Color.BLACK
    };

    abstract fun getQuarterDate(): String
    abstract fun getQuarterLabel(): String
    abstract fun getQuarterColor(): Int
}

package com.strands.covid.view.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.strands.covid.base.BaseViewModel
import com.strands.covid.domain.model.general.AffectedCountry
import com.strands.covid.domain.model.general.FailedResult
import com.strands.covid.domain.model.general.ResultCall
import com.strands.covid.domain.model.generator.DateFormatGenerator
import com.strands.covid.domain.model.generator.DateFormatGenerator.DATE
import com.strands.covid.domain.model.generator.DateFormatGenerator.ISO8601
import com.strands.covid.domain.model.generator.DateFormatGenerator.YEAR
import com.strands.covid.domain.model.generator.DateParserGenerator
import com.strands.covid.domain.usecase.ConfirmedCasesByCountryUseCase
import com.strands.covid.enums.QuarterOfYear
import com.strands.covid.enums.QuarterOfYear.FOUR
import com.strands.covid.enums.QuarterOfYear.ONE
import com.strands.covid.enums.QuarterOfYear.THREE
import com.strands.covid.enums.QuarterOfYear.TWO
import com.strands.covid.util.StateWrapper
import com.strands.covid.util.emptyInt
import com.strands.covid.util.orEmpty

class DetailViewModel(
    private val confirmedCasesByCountryUseCase: ConfirmedCasesByCountryUseCase
) : BaseViewModel() {

    private val _state = MutableLiveData<StateWrapper<State>>()
    val state: LiveData<StateWrapper<State>> = _state

    sealed class Event {
        data class OnCreate(val affectedCountry: AffectedCountry) : Event()
    }

    sealed class State {
        data class ShowLoading(val isLoading: Boolean) : State()
        data class ShowAffectedCountryInfo(val affectedCountry: AffectedCountry) : State()
        data class ShowAffectedCountryBarChart(
            val barData: BarData,
            val barChartTitle: List<String>
        ) : State()

        data class ShowFailed(val failedResult: FailedResult) : State()
    }

    fun onEvent(event: Event) {
        when (event) {
            is Event.OnCreate -> handleOnCreate(event.affectedCountry)
        }
    }

    private fun handleOnCreate(affectedCountry: AffectedCountry) = launchJob {
        setState(State.ShowLoading(true))
        apiDelay(1000)
        when (val result = confirmedCasesByCountryUseCase.invoke(affectedCountry.countrySlug)) {
            is ResultCall.Failed -> setState(State.ShowFailed(result.failedResult))
            is ResultCall.Success -> handleOnLoadConfirmedCasesByCountry(
                result.data,
                affectedCountry
            )
        }
        setState(State.ShowLoading(false))
    }

    private fun handleOnLoadConfirmedCasesByCountry(
        jsonString: String,
        affectedCountry: AffectedCountry
    ) {
        val typeToken = object : TypeToken<List<AffectedCountry>>() {}.type
        val countryInfoList = Gson().fromJson<List<AffectedCountry>>(jsonString, typeToken)
        handleGraphData(countryInfoList)
        setState(State.ShowAffectedCountryInfo(affectedCountry))
    }

    private fun handleGraphData(countryInfoList: List<AffectedCountry>) {
        val startDate = getDateFormat(countryInfoList.first().date, DATE)
        val endDate = getDateFormat(countryInfoList.last().date, DATE)
        val startYear = getDateFormat(countryInfoList.first().date, YEAR).toInt()
        val yearBetween = DateParserGenerator.getYearsBetween(startDate, endDate)
        val firstQuarter = getBarDataSet(countryInfoList, startYear, yearBetween, ONE)
        val secondQuarter = getBarDataSet(countryInfoList, startYear, yearBetween, TWO)
        val thirdQuarter = getBarDataSet(countryInfoList, startYear, yearBetween, THREE)
        val fourthQuarter = getBarDataSet(countryInfoList, startYear, yearBetween, FOUR)
        val barData = BarData(firstQuarter, secondQuarter, thirdQuarter, fourthQuarter)
        val barChartTitle = mutableListOf<String>()

        for (i in emptyInt() until yearBetween) {
            val currentYear = "${(startYear + i)}"
            barChartTitle.add(currentYear)
        }
        setState(State.ShowAffectedCountryBarChart(barData, barChartTitle))
    }

    private fun getDateFormat(dateInIso: String, expectedFormat: String): String {
        return DateFormatGenerator.convertDate(
            date = dateInIso,
            currentFormat = ISO8601,
            expectedFormat = expectedFormat
        )
    }

    private fun getBarDataSet(
        countryInfoList: List<AffectedCountry>,
        startYear: Int,
        yearBetween: Int,
        quarterOfYear: QuarterOfYear
    ): BarDataSet {
        val entries = mutableListOf<BarEntry>()

        for (i in emptyInt() until yearBetween) {
            val currentYear = "${(startYear + i)}-" + quarterOfYear.getQuarterDate()
            val totalCases = countryInfoList.find {
                getDateFormat(it.date, DATE).contains(currentYear)
            }?.totalCases

            if (totalCases != null) entries.add(
                BarEntry(
                    (i + 1).toFloat(),
                    totalCases.toFloat().orEmpty()
                )
            )
        }
        val barDataSet = BarDataSet(entries, quarterOfYear.getQuarterLabel())
        barDataSet.color = quarterOfYear.getQuarterColor()

        return barDataSet
    }

    private fun setState(state: State) {
        _state.value = StateWrapper(state)
    }
}

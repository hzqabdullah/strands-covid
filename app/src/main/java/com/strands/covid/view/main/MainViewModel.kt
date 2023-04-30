package com.strands.covid.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.strands.covid.base.BaseViewModel
import com.strands.covid.domain.model.general.AffectedCountry
import com.strands.covid.domain.model.general.FailedResult
import com.strands.covid.domain.model.general.ResultCall
import com.strands.covid.domain.model.response.SummaryCases
import com.strands.covid.domain.usecase.SummaryCasesUseCase
import com.strands.covid.enums.CountrySortByType
import com.strands.covid.util.StateWrapper

class MainViewModel(private val summaryCasesUseCase: SummaryCasesUseCase) : BaseViewModel() {

    private var summaryCases: SummaryCases = SummaryCases()
    private var countryList: List<AffectedCountry> = emptyList()

    private val _state = MutableLiveData<StateWrapper<State>>()
    val state: LiveData<StateWrapper<State>> = _state

    sealed class Event {
        object OnCreate : Event()
        data class OnSearchCountry(val searchText: String) : Event()
        data class OnSortCountryByType(val sortByType: CountrySortByType) : Event()
    }

    sealed class State {
        data class ShowLoading(val isLoading: Boolean) : State()
        data class ShowCountryList(val countries: List<AffectedCountry>) : State()
        data class ShowFailed(val failedResult: FailedResult) : State()
        data class ShowEmptyCountryList(val isEmpty: Boolean) : State()
    }

    fun onEvent(event: Event) {
        when (event) {
            is Event.OnCreate -> handleOnCreate()
            is Event.OnSearchCountry -> handleOnSearchCountry(event.searchText)
            is Event.OnSortCountryByType -> handleOnSortCountryByType(event.sortByType)
        }
    }

    private fun handleOnCreate() = launchJob {
        setState(State.ShowLoading(true))
        apiDelay(1000)
        when (val result = summaryCasesUseCase.invoke()) {
            is ResultCall.Failed -> setState(State.ShowFailed(result.failedResult))
            is ResultCall.Success -> handleOnLoadSummaryCases(result.data)
        }
        setState(State.ShowLoading(false))
    }

    private fun handleOnLoadSummaryCases(jsonString: String) {
        summaryCases = Gson().fromJson(jsonString, SummaryCases::class.java)
        countryList = summaryCases.countries
        handleCountryList(countryList)
    }

    private fun handleOnSearchCountry(searchText: String) {
        if (countryList.isEmpty()) return
        val filterList = countryList.filter {
            it.countryName.contains(searchText, true) || it.countryCode.contains(searchText, true)
        }
        handleCountryList(filterList)
    }

    private fun handleOnSortCountryByType(sortByType: CountrySortByType) {
        if (countryList.isEmpty()) return
        val filterList = sortByType.getFilteredCountryList(countryList)
        handleCountryList(filterList)
    }

    private fun handleCountryList(countries: List<AffectedCountry>) {
        setState(State.ShowCountryList(countries))
        setState(State.ShowEmptyCountryList(countries.isEmpty()))
    }

    private fun setState(state: State) {
        _state.value = StateWrapper(state)
    }
}

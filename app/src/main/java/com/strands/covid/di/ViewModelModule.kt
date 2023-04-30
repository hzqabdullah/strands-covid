package com.strands.covid.di

import com.strands.covid.view.detail.DetailViewModel
import com.strands.covid.view.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val viewModelModule = module {
    viewModel { MainViewModel(summaryCasesUseCase = get()) }
    viewModel { DetailViewModel(confirmedCasesByCountryUseCase = get()) }
}

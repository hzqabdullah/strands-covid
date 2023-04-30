package com.strands.covid.di

import com.strands.covid.domain.usecase.ConfirmedCasesByCountryUseCase
import com.strands.covid.domain.usecase.SummaryCasesUseCase
import org.koin.dsl.module

internal val useCaseModule = module {
    factory { SummaryCasesUseCase(get()) }
    factory { ConfirmedCasesByCountryUseCase(get()) }
}

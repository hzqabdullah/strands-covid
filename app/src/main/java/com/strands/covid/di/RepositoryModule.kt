package com.strands.covid.di

import com.strands.covid.domain.repository.CovidCaseRepository
import com.strands.covid.domain.repository.CovidCaseRepositoryImpl
import org.koin.dsl.module

internal val repositoryModule = module {
    factory<CovidCaseRepository> { CovidCaseRepositoryImpl() }
}

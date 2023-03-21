package com.youtop.domain.di

import org.koin.dsl.module

val domainKoinModule = module {
    includes(useCaseKoinModule)
}
package com.youtop.data.di

import org.koin.dsl.module

val dataKoinModule = module {
    includes(networkModule, dataSourceModule, repositoryModule)
}
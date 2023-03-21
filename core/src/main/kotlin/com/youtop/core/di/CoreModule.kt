package com.youtop.core.di

import org.koin.dsl.module

val coreKoinModules = module {
    includes(coroutineModule)
}
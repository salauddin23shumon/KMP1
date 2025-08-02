package org.s1s.project.di.init

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.s1s.project.di.module.appModule

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(appModule)
}

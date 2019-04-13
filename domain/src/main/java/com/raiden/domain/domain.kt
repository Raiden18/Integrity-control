package com.raiden.domain

import com.raiden.domain.interactors.info.InfoInteractor
import com.raiden.domain.interactors.info.InfoInteractorImpl
import org.koin.dsl.module.module
import org.koin.experimental.builder.singleBy

val domain = module{
    singleBy<InfoInteractor, InfoInteractorImpl>()
}
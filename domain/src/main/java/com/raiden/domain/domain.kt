package com.raiden.domain

import com.raiden.domain.interactors.applications.ApplicationsInteractor
import com.raiden.domain.interactors.applications.ApplicationsInteractorImpl
import com.raiden.domain.interactors.contacts.ContactsInteractor
import com.raiden.domain.interactors.contacts.ContactsInteractorImpl
import com.raiden.domain.interactors.files.FilesInteractor
import com.raiden.domain.interactors.files.FilesInteractorImpl
import com.raiden.domain.interactors.info.InfoInteractor
import com.raiden.domain.interactors.info.InfoInteractorImpl
import org.koin.dsl.module.module
import org.koin.experimental.builder.singleBy

val domain = module {
    singleBy<InfoInteractor, InfoInteractorImpl>()
    singleBy<ApplicationsInteractor, ApplicationsInteractorImpl>()
    singleBy<FilesInteractor, FilesInteractorImpl>()
    singleBy<ContactsInteractor, ContactsInteractorImpl>()
}
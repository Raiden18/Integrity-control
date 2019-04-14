package com.raiden.karpukhinomgupsdiplom.info

import androidx.lifecycle.ViewModel
import com.nhaarman.mockitokotlin2.mock
import com.raiden.domain.interactors.info.InfoInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Unconfined
import org.junit.Before
import org.junit.Test


class InfoViewModelTest{
    lateinit var viewModel: ViewModel
    lateinit var interactor: InfoInteractor
    val context = Dispatchers.Unconfined

    @Before
    fun setUp(){
        interactor = mock()
        viewModel = InfoViewModel(interactor, context)
    }

    @Test
    fun `Shoud update `(){

    }
}
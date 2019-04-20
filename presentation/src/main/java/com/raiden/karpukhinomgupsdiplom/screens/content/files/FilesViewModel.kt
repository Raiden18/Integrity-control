package com.raiden.karpukhinomgupsdiplom.screens.content.files

import com.raiden.domain.interactors.files.FilesInteractor
import com.raiden.karpukhinomgupsdiplom.converters.convertToUi
import com.raiden.karpukhinomgupsdiplom.screens.content.common.ContentViewModel
import com.raiden.karpukhinomgupsdiplom.uimodels.UiContent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class FilesViewModel(
    private val filesInteractor: FilesInteractor,
    IO: CoroutineDispatcher = Dispatchers.Main,
    DEFAULT: CoroutineDispatcher = Dispatchers.Default
) : ContentViewModel(IO, DEFAULT) {

    override suspend fun loadDeviceContent(): List<UiContent> {
        return filesInteractor.getDeviceFiles().toList().convertToUi()
    }

    override suspend fun loadSavedContent(): List<UiContent> {
        return filesInteractor.getSavedFiles().toList().convertToUi()
    }

    override fun calculateChanged() {
        //TODO::NOT Implemented
    }
}
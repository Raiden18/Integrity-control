package com.raiden.karpukhinomgupsdiplom.content.files

import com.raiden.domain.interactors.files.FilesInteractor
import com.raiden.karpukhinomgupsdiplom.content.common.UiContentViewModel
import com.raiden.karpukhinomgupsdiplom.content.common.models.UiContent
import com.raiden.karpukhinomgupsdiplom.content.files.model.convertToUi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class FilesViewModel(
    private val filesInteractor: FilesInteractor,
    IO: CoroutineDispatcher = Dispatchers.Main,
    DEFAULT: CoroutineDispatcher = Dispatchers.Default
) : UiContentViewModel(IO, DEFAULT) {
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
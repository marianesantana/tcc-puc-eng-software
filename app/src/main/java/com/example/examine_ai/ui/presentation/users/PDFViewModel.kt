package com.example.examine_ai.ui.presentation.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.io.InputStream


class PDFViewModel: ViewModel() {

    private val _uiState = MutableLiveData<PDFDataState>()
    val uiState: LiveData<PDFDataState> = _uiState
    private val _isDialogVisible = MutableLiveData(false)
    val isDialogVisible: LiveData<Boolean> = _isDialogVisible

    private var extractedText: String? = null
    private var caminho: String = ""

    fun updateValue(value: String) {
        _uiState.value = _uiState.value?.copy(extractedText = value)
    }

    fun definirTextoAdicionado(texto: String) {
        extractedText = texto
    }

    fun obterExtractedText(): String? {
        return extractedText
    }

    fun updateExtractedValue(value: InputStream) {
        _uiState.value = _uiState.value?.copy(inputStream = value)
    }

    fun showDialog() {
        _isDialogVisible.value = true
    }

    fun hideDialog() {
        _isDialogVisible.value = false
    }

    fun definirCaminho(texto: String) {
        caminho = texto
    }

    fun obterCaminho(): String {
        return caminho
    }
}
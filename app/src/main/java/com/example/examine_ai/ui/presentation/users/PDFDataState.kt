package com.example.examine_ai.ui.presentation.users

import java.io.InputStream

data class PDFDataState(
    var extractedText: String? = null,
    var inputStream: InputStream? = null,
    var isDialogVisible: Boolean? = false
)

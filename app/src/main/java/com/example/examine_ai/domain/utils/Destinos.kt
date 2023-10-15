package com.example.examine_ai.domain.utils

object Destinos {
    const val LISTA_EXAMES = "exames"
    const val DETALHES_EXAME = "exame/{exameId}"
    const val HOME = "home"

    const val PREVIEW_PDF = "pdfPreview"

    object Argumentos {
        const val EXAME_ID = "exameId"
        const val INPUT_STREAM = "inputStream"
    }
}
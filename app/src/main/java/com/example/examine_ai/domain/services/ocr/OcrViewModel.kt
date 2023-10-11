package com.example.examine_ai.domain.services.ocr

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class OcrViewModel(application: Application) : AndroidViewModel(application) {
    private val _capturedText = MutableLiveData<String>()
    val capturedText: LiveData<String> = _capturedText

//    fun processImage(bitmap: Bitmap) {
//        val detector = FirebaseVision.getInstance().onDeviceTextRecognizer
//        val firebaseImage = FirebaseVisionImage.fromBitmap(bitmap)
//        detector.processImage(firebaseImage).addOnSuccessListener { texts ->
//            val stringBuilder = StringBuilder()
//            for (block in texts.textBlocks) {
//                for (line in block.lines) {
//                    for (element in line.elements) {
//                        stringBuilder.append(element.text + " ")
//                    }
//                    stringBuilder.append("\n")
//                }
//            }
//            _capturedText.value = stringBuilder.toString()
//        }
//    }
}

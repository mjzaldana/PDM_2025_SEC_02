package com.example.ml_kit_test.viewModel

import android.graphics.Bitmap
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions

class TextRecognizerViewModel: ViewModel(){
    var resultText = mutableStateOf("")

    fun recognizeText(bitmap: Bitmap){
        val image = InputImage.fromBitmap(bitmap, 0)
        val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

        recognizer.process(image)
            .addOnSuccessListener { visionText ->
                resultText.value = visionText.text
            }
            .addOnFailureListener {
                resultText.value = "Error al reconocer texto"
            }
    }
}
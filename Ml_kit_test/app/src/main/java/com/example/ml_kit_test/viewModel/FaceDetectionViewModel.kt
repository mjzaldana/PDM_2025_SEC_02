package com.example.ml_kit_test.viewModel

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetectorOptions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FaceDetectionViewModel: ViewModel(){
    private val _facesInfo = MutableStateFlow<List<String>>(emptyList())
    val facesInfo = _facesInfo.asStateFlow()

    fun detectFaces(bitmap: Bitmap)
        val image = InputImage.fromBitmap(bitmap, 0)

        val detectorOptions = FaceDetectorOptions.Builder()
            .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_FAST)
            .setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_ALL)
            .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_ALL)
            .build()

        val detector = FaceDetection.getClient(detectorOptions)

        detector.process(image)
            .addOnSuccessListener { faces ->
                val results = faces.map { face ->
                    val smilingProb = face.smilingProbability?.let {
                        "Sonrisa: ${(it*100).toInt()}%"
                    }
                    val leftEyeOpen = face.leftEyeOpenProbability?.let {
                        "Ojo izquierdo abierto: ${(it*100).toInt()}%"
                    }
                    val rightEyeOpen = face.rightEyeOpenProbability?.let {
                        "Ojo derecho abierto: ${(it*100).toInt()}%"
                    }
                    val bounds = face.boundingBox

                    "${smilingProb}, ${leftEyeOpen}, ${rightEyeOpen}"
                    //"Posicion: ${bounds.left}, ${bounds.right}"
                }
                _facesInfo.value = results
            }
            .addOnFailureListener {
                _facesInfo.value = listOf("Error al detectar rostro")
            }
    }
}
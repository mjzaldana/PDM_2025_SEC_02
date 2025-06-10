package com.example.ml_kit_test.ui.textRecognizer

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import java.io.InputStream

@Composable
fun CameraCapture(onImageCaptured: (Bitmap) -> Unit){
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            val stream: InputStream? = context
                .contentResolver.openInputStream(uri)
            val bitmap = BitmapFactory.decodeStream(stream)
            onImageCaptured(bitmap)
        }
    }

    Button(
        onClick = {
            launcher.launch("image/*")
        }
    ) {
        Text("Seleccionar Imagen")
    }
}
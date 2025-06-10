package com.example.ml_kit_test.ui.faceDetection

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ml_kit_test.viewModel.FaceDetectionViewModel
import java.io.InputStream

@Composable
fun FaceDetectionScreen(viewModel: FaceDetectionViewModel = viewModel()){
    val context = LocalContext.current
    var selectedBitmap by remember { mutableStateOf<Bitmap?>(null) }
    val facesInfo by viewModel.facesInfo.collectAsState()
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            val stream: InputStream? = context
                .contentResolver.openInputStream(uri)
            val bitmap = BitmapFactory.decodeStream(stream)
            selectedBitmap = bitmap
            bitmap?.let{viewModel.detectFaces(it)}
        }
    }
    Column {
        Button(
            onClick = {launcher.launch("image/*")}
        ) {
            Text("Seleccionar imagen")
        }
    }

    Spacer(modifier= Modifier.height(16.dp))

    selectedBitmap?.let{ bitmap ->
        Image(
            bitmap = bitmap.asImageBitmap(),
            contentDescription = "Imagen",
            modifier = Modifier.fillMaxWidth().height(300.dp)
        )
    }

    Spacer(modifier= Modifier.height(16.dp))

    Text("Resultado de la deteccion")
    facesInfo.forEachIndexed { index, info->
        Text("Rostro #${index + 1} : ${info}")
    }
}
package com.example.ml_kit_test.ui.textRecognizer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TextResultScreen(text: String){
    Column (
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    ){
        Text("Texto detectado")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text)
    }
}
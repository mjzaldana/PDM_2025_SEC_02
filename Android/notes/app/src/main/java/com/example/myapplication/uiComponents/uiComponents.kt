package com.example.myapplication.uiComponents

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FormularioUI(){
    var name by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var accept by remember { mutableStateOf(false) }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Nombre",
        )
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Ingresa tu nombre") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = "Genero",
        )

        Spacer(modifier = Modifier.width(8.dp))

        Row (verticalAlignment = Alignment.CenterVertically){

            Text(
                text = "Masculino",
            )

            RadioButton(
                selected = gender == "Masculino",
                onClick = { gender = "Masculino" }
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "Femenino",
            )

            RadioButton(
                selected = gender == "Femenino",
                onClick = { gender = "Femenino" }
            )
        }

        Row (verticalAlignment = Alignment.CenterVertically){
            Checkbox(
                checked = accept,
                onCheckedChange = { accept = it}
            )
            Text(
                text = "Acepto los terminos y condiciones",
            )
        }

        Button(
            onClick = {
                if(name.isBlank()){
                    Toast.makeText(context, "Porfavor rellena el nombre", Toast.LENGTH_SHORT).show()
                    return@Button
                }
                if(!accept){
                    Toast.makeText(context, "Porfavor acepta los terminos y condiciones", Toast.LENGTH_SHORT).show()
                    return@Button
                }
                val mensaje = "Nombre: ${name}"
                Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show()
            }
        ) {
            Text( text = "Enviar")
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFormularioUI(){
    FormularioUI()
}
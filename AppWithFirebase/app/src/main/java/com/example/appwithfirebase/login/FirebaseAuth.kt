package com.example.appwithfirebase.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.crashlytics.FirebaseCrashlytics

@Composable
fun FirebaseAuthExample() {
    val auth = FirebaseAuth.getInstance()
    val user = remember { mutableStateOf(auth.currentUser) }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        val listener = FirebaseAuth.AuthStateListener {
            user.value = it.currentUser
        }
        auth.addAuthStateListener(listener)
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        if (user.value != null) {
            Text("Bienvenido: ${user.value?.email}")
            Spacer(Modifier.height(16.dp))
            Button (onClick = { auth.signOut() }) {
                Text("Cerrar sesión")
            }
        } else {
            OutlinedTextField(
                value = email.value,
                onValueChange = { email.value = it },
                label = { Text("Email") }
            )
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = password.value,
                onValueChange = { password.value = it },
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(Modifier.height(16.dp))
            Button(onClick = {
                auth.signInWithEmailAndPassword(email.value, password.value)
                    .addOnFailureListener {
                        auth.createUserWithEmailAndPassword(email.value, password.value)
                    }
            }) {
                Text("Iniciar sesión / Registrar")
            }
            Button(onClick = {
                FirebaseCrashlytics.getInstance().log("Provocando un error")
                throw RuntimeException("Crash de prueba")
            }) {
                Text("Provocar error")
            }
        }
    }
}


package com.example.appwithfirebase.analytics

import android.os.Bundle
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase

@Composable
fun AnalyticsScreen() {
    val context = LocalContext.current
    val analytics = remember { Firebase.analytics }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Firebase Analytics", style = MaterialTheme.typography.headlineSmall)

        Button(onClick = {
            analytics.logEvent(FirebaseAnalytics.Event.LOGIN, null)
        }) {
            Text("Log In Event")
        }

        Button(onClick = {
            val bundle = Bundle().apply {
                putString("item_id", "1234")
                putString("item_name", "Producto Demo")
                putString("item_category", "test")
            }
            analytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM, bundle)
        }) {
            Text("Seleccionar ítem")
        }

        Button(onClick = {
            val bundle = Bundle().apply {
                putString(FirebaseAnalytics.Param.ITEM_ID, "id_demo_001")
                putString(FirebaseAnalytics.Param.ITEM_NAME, "Producto Demo")
                putString(FirebaseAnalytics.Param.CONTENT_TYPE, "producto")
            }
            analytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)
        }) {
            Text("Seleccionar contenido")
        }

        Button(onClick = {
            val bundle = Bundle().apply {
                putString(FirebaseAnalytics.Param.CONTENT_TYPE, "imagen")
                putString(FirebaseAnalytics.Param.ITEM_ID, "img_123")
            }
            analytics.logEvent(FirebaseAnalytics.Event.SHARE, bundle)
        }) {
            Text("Compartir contenido")
        }

        Button(onClick = {
            val bundle = Bundle().apply {
                putString("screen_name", "AnalyticsScreen")
                putString("category", "interacción")
            }
            analytics.logEvent("pantalla_abierta", bundle)
        }) {
            Text("Pantalla Abierta")
        }
    }
}
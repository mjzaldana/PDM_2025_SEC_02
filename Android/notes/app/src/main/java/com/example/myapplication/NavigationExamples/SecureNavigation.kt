package com.example.myapplication.NavigationExamples

import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import kotlinx.serialization.Serializable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

data class User(val name: String, val age: Int)

@Composable
fun NavigationStackExample(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "screenA"){
        composable("screenA") {
            ScreenA(navController = navController)
        }
        composable("screenB") {
            backStackEntry ->
            val userJson = backStackEntry.arguments?.getString("usewrJson")
            val user = Json.decodeFromString<User>(userJson ?: "Error")
            ScreenB(navController = navController, user = user)
        }
        composable("screenC") {
            ScreenC(navController = navController)
        }
        composable("screenD") {
            ScreenD(navController = navController)
        }
        /*
        composable("screenE") {
            ScreenA()
        }
         */
    }
}

@Composable
fun ScreenA(navController: NavController){
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }

    Column {
        Text("Screen A")
        TextField(value = name, onValueChange = {name = it}, label = {Text("Name")})
        TextField(value = age, onValueChange = {age = it}, label = {Text("Age")})
        Button(onClick = {
            val user = User(name, age.toIntOrNull() ?: 0)
            val userJson = Json.encodeToString(user)
            navController.navigate("/screenB/${userJson}")
        }) {
            Text("Go to Screen B with user info")
        }
    }
}

@Composable
fun ScreenB(navController: NavController, user: User){
    Column {
        Text("Screen B")
        Text("User name: ${user.name}")
        Text("User age: ${user.age}")
        Button(onClick = {
            { navController.navigate("screenC") }
        }) {
            Text("Go to Screen C")
        }
    }
}

@Composable
fun ScreenC(navController: NavController){
    Column {
        Text(
            "Screen C"
        )
        Button(
            onClick = {
                navController.navigate("ScreenD")
            }
        ) {
            Text("Go to screen D")
        }
    }
}

@Composable
fun ScreenD(navController: NavController){
    Column {
        Text(
            "Screen D"
        )
        Button(
            onClick = {
                navController.popBackStack("ScreenA", inclusive = false)
            }
        ) {
            Text("Go to screen A")
        }
        Button(
            onClick = {
                navController.navigate("ScreenE")
            }
        ) {
            Text("Go to screen E")
        }
    }
}

@Composable
fun ScreenE(navController: NavController){
    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    val currentOnBackPressed = remember {
        object : OnBackPressedCallback(true){
            override fun handleOnBackPressed(){
                navController.popBackStack("ScreenA", inclusive = false)
            }
        }
    }

   DisposableEffect(onBackPressedDispatcher) {
       onBackPressedDispatcher?.addCallback(currentOnBackPressed)
       onDispose {
           currentOnBackPressed.remove()
       }
   }

   Column {
       Text("Screen E")
       Text("Click atras para volver a la pantalla A")
   }
}





package com.example.newmoneyapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newmoneyapp.ui.List.CurrencyListScreen
import com.example.newmoneyapp.ui.converter.CurrencyConverterScreen

sealed class Screen(val route: String){
    object CurrencyList: Screen("currency_list")
    object CurrencyConverter: Screen("currency_converter/{currencyCode}")
}

@Composable
fun NavGraph(navController: NavHostController){
    NavHost(navController = navController, startDestination = Screen.CurrencyList.route){
        composable(Screen.CurrencyList.route) {
            CurrencyListScreen(onNavigateToConverter = {
                currency -> navController.navigate("currency_converter/$currency")
            })
        }
        composable(Screen.CurrencyConverter.route) {
            CurrencyConverterScreen()
        }
    }
}
package com.example.newmoneyapp.ui.List

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import org.w3c.dom.Text
import androidx.compose.runtime.*
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.MaterialTheme.colorScheme

@Composable
fun CurrencyListScreen(
    viewModel: CurrencyListViewModel = hiltViewModel(),
    onNavigateToConverter: (String) -> Unit
){
    val uiState by viewModel.uiState.collectAsState()

    Column {
        Text(text = "Lista de monedas")
        if(uiState.loading){
            Column {
                CircularProgressIndicator()
            }
        }else if( uiState.error != null ){
            Text(text = "Ha ocurrido un error")
        }else{
            LazyColumn {
                items(uiState.rates.keys.toList()) { currencyCode ->
                    Text(
                        text = currencyCode,
                        modifier = Modifier.clickable{(onNavigateToConverter(currencyCode))}
                    )
                }
            }
        }
    }
}
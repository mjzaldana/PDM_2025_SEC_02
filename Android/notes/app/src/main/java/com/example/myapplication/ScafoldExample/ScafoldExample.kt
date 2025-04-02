package com.example.myapplication.ScafoldExample

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch

data class Criptomoneda(
    val id: Int,
    val nombre: String,
    val simbolo: String,
    val precio: Double,
    val cambio24h: Double,
)

@Composable
fun mainBottomBar(navController: NavController) {
    BottomAppBar {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            TextButton(onClick = { navController.navigate("favoritos") }) { Text("Favoritos") }
            TextButton(onClick = { navController.navigate("mercado") }) { Text("Mercado") }
            TextButton(onClick = { navController.navigate("noticias") }) { Text("Noticias") }
            TextButton(onClick = { navController.navigate("criptomonedas") }) { Text("Criptomonedas") }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CryptoApp() {
    val criptomonedas = remember {
        mutableStateListOf(
            Criptomoneda(1, "Bitcoin", "BTC", 45000.0, 2.5),
            Criptomoneda(2, "Ethereum", "ETH", 3000.0, -1.2),
            Criptomoneda(3, "Cardano", "ADA", 1.5, 0.8)
        )
    }

    //Lo usaremos para abrir o cerrar el menu
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    //Permite lanzar tareas asíncronas, como abrir/cerrar el drawer sin bloquear la UI.
    val coroutineScope = rememberCoroutineScope()

    //Crea el estado para mostrar notificaciones tipo Snackbar (mensajes emergentes).
    val snackbarHostState = remember { SnackbarHostState() }

    val navController = rememberNavController()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text("Menú", style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.height(16.dp))
                TextButton(onClick = { /*  */ }) { Text("Inicio") }
                TextButton(onClick = { /* */ }) { Text("Perfil") }
                TextButton(onClick = { /* */ }) { Text("Ajustes") }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Criptomonedas") },
                    navigationIcon = {
                        IconButton(onClick = {
                            coroutineScope.launch { drawerState.open() }
                        }) {
                            Icon(Icons.Filled.Menu, contentDescription = "Menú")
                        }
                    },
                    actions = {
                        IconButton(onClick = { /* Lógica de búsqueda */ }) {
                            Icon(Icons.Filled.Search, contentDescription = "Buscar")
                        }
                    }
                )
            },
            bottomBar = { mainBottomBar(navController)
            },
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    criptomonedas.add(Criptomoneda(4, "Solana", "SOL", 120.0, 3.2))
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar("Solana agregada")
                    }
                }) {
                    Icon(Icons.Filled.Add, contentDescription = "Agregar Criptomoneda")
                }
            },
            snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
        ) { innerPadding ->
            NavHost(navController, startDestination = "favoritos", Modifier.padding(innerPadding)) {
                composable("favoritos") { FavoritosScreen() }
                composable("mercado") { MercadoScreen() }
                composable("noticias") { NoticiasScreen() }
                composable("criptomonedas") { CryptoList(criptomonedas = criptomonedas, modifier = Modifier.padding(innerPadding)) }
            }
        }
    }
}

@Composable
fun FavoritosScreen() {
    Text("Pantalla de Favoritos")
}

@Composable
fun MercadoScreen() {
    Text("Pantalla de Mercado")
}

@Composable
fun NoticiasScreen() {
    Text("Pantalla de Noticias")
}

@Composable
fun CryptoList(criptomonedas: List<Criptomoneda>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(criptomonedas) { criptomoneda ->
            CryptoItem(criptomoneda = criptomoneda)
        }
    }
}

@Composable
fun CryptoItem(criptomoneda: Criptomoneda) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = criptomoneda.nombre, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(text = criptomoneda.simbolo, color = Color.Gray)
            }
            Spacer(modifier = Modifier.weight(1f))
            Column(horizontalAlignment = Alignment.End) {
                Text(text = "$${criptomoneda.precio}", fontWeight = FontWeight.SemiBold)
                val cambioColor = if (criptomoneda.cambio24h >= 0) Color.Green else Color.Red
                Text(text = "${criptomoneda.cambio24h}%", color = cambioColor)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CryptoApp()
}


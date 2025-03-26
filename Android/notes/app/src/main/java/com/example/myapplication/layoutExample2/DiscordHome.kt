package com.example.test_app.LayoutExample2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.runtime.remember

@Composable
fun DiscordMainScreen(){
    Column (
        modifier = Modifier.fillMaxSize()
    ){
        Row (){
            ServerList()
            Column() {
                TopBar()
                SearchAndActions()
            }
        }
        //BottomNavigationBar()
    }
}

@Composable
fun ServerList(){

    var servers = remember { mutableListOf(1,2,3,4,5) }

    Column (
        modifier = Modifier
            .fillMaxHeight()
            .width(70.dp)
            .background(Color(0xFF2C2F33))
    ){
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(servers) {
                server ->
                Box (
                    modifier = Modifier
                        .padding(8.dp)
                        .size(50.dp)
                        .clip(CircleShape)
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ){
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(){
    TopAppBar(
        title = {Text(text = "Server Name", color = Color.Black)}
    )
}

@Composable
fun SearchAndActions(){
    var searchText = remember {
        androidx.compose.runtime.mutableStateOf(TextFieldValue(""))
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Magenta)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BasicTextField(
            value = searchText.value,
            onValueChange = { searchText.value = it },
            singleLine = true,
            decorationBox = {
                innerTextField ->
                    Row {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Search Icon"
                        )
                    }
            }
        )
    }
}

@Preview
@Composable
fun PreviewDiscordMainScreen(){
    DiscordMainScreen()
}
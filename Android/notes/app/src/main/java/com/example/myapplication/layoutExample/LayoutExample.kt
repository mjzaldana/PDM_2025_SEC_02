package com.example.myapplication.layoutExample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun LayoutExample(){
    Column (
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ){
        Row (
            modifier = Modifier.fillMaxWidth().height(60.dp).background(Color.LightGray),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Box(modifier = Modifier.size(50.dp).background(Color.Green))
            Box(modifier = Modifier.size(50.dp).background(Color.Red))
            Box(modifier = Modifier.size(50.dp).background(Color.Blue))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row (
            modifier = Modifier.fillMaxWidth().height(50.dp).background(Color.LightGray),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            Box(modifier = Modifier.size(50.dp).background(Color(125, 45, 89)))
            Box(modifier = Modifier.size(50.dp).background(Color(252, 245, 155)))
            Box(modifier = Modifier.size(50.dp).background(Color.Blue))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row (
            modifier = Modifier.fillMaxWidth().height(50.dp).background(Color.LightGray),
            horizontalArrangement = Arrangement.SpaceAround
        ){
            Box(modifier = Modifier.size(50.dp).background(Color(125, 45, 89)))
            Box(modifier = Modifier.size(50.dp).background(Color(252, 245, 155)))
            Box(modifier = Modifier.size(50.dp).background(Color.Blue))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column (
            modifier = Modifier.fillMaxWidth().height(50.dp).background(Color.LightGray),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text("Elemento 1", color = Color.Magenta)
            Text("Elemento 2", color = Color.Magenta)
            Text("Elemento 3", color = Color.Magenta)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier.size(80.dp).background(Color.Black),
            contentAlignment = Alignment.Center
        ){
            Box(modifier = Modifier.size(50.dp).background(Color.Yellow))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(100.dp)
                .background(Color.Black),
            contentAlignment = Alignment.Center
        ){
            Box(modifier = Modifier.size(50.dp).background(Color.Yellow))
        }
    }
}
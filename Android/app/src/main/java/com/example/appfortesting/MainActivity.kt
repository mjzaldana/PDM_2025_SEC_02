package com.example.appfortesting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appfortesting.ui.theme.AppForTestingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppForTestingTheme {
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(16.dp)
                    )
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier.testTag("greetingText")
    )
}

@Composable
fun ShowIfNeeded(show: Boolean){
    if(show){
        Text("Visible", modifier = Modifier.testTag("visibilityText"))
    }
}

@Composable
fun ToggleText(){
    var visible by remember { mutableStateOf(false) }

    Column {
        Button(
            onClick = {visible = !visible},
            modifier = Modifier.testTag("toggleButton")
        ) {
            Text("Toggle")
        }

        if(visible){
            Text("Now yoou see me", modifier = Modifier.testTag("toggledText"))
        }
    }
}

@Composable
fun TextInput(){
    var text by remember {mutableStateOf("")}

    Column {
        TextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier.testTag("inputField")
        )
        Text("Typed: $text", modifier = Modifier.testTag("outputText"))
    }
}

@Composable
fun ItemList(){
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("list")
    ) {
        items(50){ index ->
            Text(
                text = "Item #$index",
                modifier = Modifier
                    .padding(8.dp)
                    .testTag("item_$index")
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppForTestingTheme {
        Greeting("Android")
    }
}
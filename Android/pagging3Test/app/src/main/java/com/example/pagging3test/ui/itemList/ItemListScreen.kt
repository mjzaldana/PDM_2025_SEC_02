package com.example.pagging3test.ui.itemList

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pagging3test.model.Item

@Composable
fun ItemListScreen(viewModel: ItemListViewModel = hiltViewModel()){
    val itemsWithPaging = viewModel.items.collectAsLazyPagingItems()

    LazyColumn {
        items(itemsWithPaging.itemCount) {
            index -> itemsWithPaging[index]?.let {
                item -> ItemRow(item)
            }
        }
    }
}

@Composable
fun ItemRow(item: Item){
    Column {
        Text(
            text = item.name,
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            fontSize = 15.sp
        )
    }
}




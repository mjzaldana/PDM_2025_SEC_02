package com.example.pagging3test.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.pagging3test.data.paging.LocalPagingSource
import com.example.pagging3test.model.Item
import javax.inject.Inject

class ItemRepository {
    private val testData = List(95){ Item(it, "Item $it") }

    fun getItems(): Pager<Int, Item>{
        return Pager(PagingConfig(pageSize = 5)){
            LocalPagingSource(testData)
        }
    }
}


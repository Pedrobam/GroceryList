package br.com.bamtech.grocerylist.domain.repository

import br.com.bamtech.grocerylist.domain.model.GroceryItem
import kotlinx.coroutines.flow.Flow

interface GroceryRepository {

    fun getItems(): Flow<List<GroceryItem>>

    suspend fun addItem(item: GroceryItem)

    suspend fun deleteItem(item: GroceryItem)

    suspend fun updateItem(item: GroceryItem)
}
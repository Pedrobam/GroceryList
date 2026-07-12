package br.com.bamtech.grocerylist.domain.repository

import br.com.bamtech.grocerylist.domain.model.GroceryItem
import kotlinx.coroutines.flow.Flow

interface GroceryRepository {

    fun observeItems(): Flow<List<GroceryItem>>

    suspend fun addItem(name: String)

    suspend fun updateItemName(id: Long, name: String)

    suspend fun deleteItem(id: Long)

    suspend fun togglePurchased(id: Long)
}
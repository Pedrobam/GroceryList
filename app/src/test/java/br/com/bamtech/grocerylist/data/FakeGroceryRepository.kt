package br.com.bamtech.grocerylist.data

import br.com.bamtech.grocerylist.domain.model.GroceryItem
import br.com.bamtech.grocerylist.domain.repository.GroceryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeGroceryRepository: GroceryRepository {

    private val items = MutableStateFlow<List<GroceryItem>>(emptyList())

    override fun observeItems(): Flow<List<GroceryItem>> {
        return items
    }

    override suspend fun addItem(item: GroceryItem) {
        val generatedItem = if (item.id == 0L) {
            item.copy(id = generateNextId())
        } else {
            item
        }

        items.value += generatedItem
    }

    override suspend fun updateItemName(id: Long, name: String) {
        items.value = items.value.map { item ->
            if (item.id == id) {
                item.copy(name = name)
            } else {
                item
            }
        }
    }

    override suspend fun deleteItem(id: Long) {
        items.value = items.value.filter { item ->
            item.id != id
        }
    }

    override suspend fun togglePurchased(id: Long) {
        items.value = items.value.map { item ->
            if (item.id == id) {
                item.copy(isPurchased = !item.isPurchased)
            } else {
                item
            }
        }
    }

    override suspend fun restoreItem(item: GroceryItem) {
        items.value += item
    }

    private fun generateNextId(): Long {
        return (items.value.maxOfOrNull { it.id } ?: 0L) + 1L
    }
}

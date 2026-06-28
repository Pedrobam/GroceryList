package br.com.bamtech.grocerylist.data.repository

import br.com.bamtech.grocerylist.data.local.GroceryDao
import br.com.bamtech.grocerylist.data.mapper.toDomain
import br.com.bamtech.grocerylist.data.mapper.toEntity
import br.com.bamtech.grocerylist.domain.model.GroceryItem
import br.com.bamtech.grocerylist.domain.repository.GroceryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GroceryRepositoryImpl @Inject constructor(
    private val dao: GroceryDao
): GroceryRepository {

    override fun observeItems(): Flow<List<GroceryItem>> {
        return dao.observeItems().map { list ->
            list.map { item ->
                item.toDomain()
            }
        }
    }

    override suspend fun addItem(item: GroceryItem) {
        dao.insert(item.toEntity())
    }

    override suspend fun deleteItem(id: Long) {
        dao.delete(id)
    }

    override suspend fun togglePurchased(id: Long) {
        dao.update(id)
    }
}
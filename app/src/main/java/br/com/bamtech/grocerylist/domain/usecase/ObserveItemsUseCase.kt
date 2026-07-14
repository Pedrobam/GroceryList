package br.com.bamtech.grocerylist.domain.usecase

import br.com.bamtech.grocerylist.domain.model.GroceryItem
import br.com.bamtech.grocerylist.domain.repository.GroceryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveItemsUseCase @Inject constructor(
    private val repository: GroceryRepository
) {

    suspend fun invoke(): Flow<List<GroceryItem>> {
        return repository.observeItems()
    }
}
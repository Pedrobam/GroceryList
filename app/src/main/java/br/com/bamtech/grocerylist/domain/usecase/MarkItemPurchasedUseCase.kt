package br.com.bamtech.grocerylist.domain.usecase

import br.com.bamtech.grocerylist.domain.repository.GroceryRepository

class MarkItemPurchasedUseCase(
    private val repository: GroceryRepository
) {

    suspend fun invoke(id: Long) {
        TODO("Not yet implemented")
    }
}

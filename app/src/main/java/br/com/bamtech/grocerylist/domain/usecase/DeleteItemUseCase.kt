package br.com.bamtech.grocerylist.domain.usecase

import br.com.bamtech.grocerylist.domain.repository.GroceryRepository

class DeleteItemUseCase(
    private val repository: GroceryRepository
) {
    suspend fun invoke(id: Long) {
        TODO("Not yet implemented")
    }
}
package br.com.bamtech.grocerylist.domain.usecase

import br.com.bamtech.grocerylist.domain.repository.GroceryRepository

class DeleteItemUseCaseImpl(
    private val repository: GroceryRepository
): DeleteItemUseCase {
    override suspend fun execute() {
        TODO("Not yet implemented")
    }
}
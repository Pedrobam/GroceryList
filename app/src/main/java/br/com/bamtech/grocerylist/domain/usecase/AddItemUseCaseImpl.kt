package br.com.bamtech.grocerylist.domain.usecase

import br.com.bamtech.grocerylist.domain.repository.GroceryRepository

class AddItemUseCaseImpl(
    private val repository: GroceryRepository
): AddItemUseCase {

    override suspend fun execute() {
        TODO("Not yet implemented")
    }
}
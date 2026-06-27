package br.com.bamtech.grocerylist.domain.usecase

import br.com.bamtech.grocerylist.domain.repository.GroceryRepository

class TogglePurchasedUseCaseImpl(
    private val repository: GroceryRepository
) : TogglePurchasedUseCase {

    override suspend fun invoke(id: Long) {
        TODO("Not yet implemented")
    }
}

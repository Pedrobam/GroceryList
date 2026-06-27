package br.com.bamtech.grocerylist.domain.usecase

import br.com.bamtech.grocerylist.domain.repository.GroceryRepository

class ObserveItemsUseCaseImpl(
    private val repository: GroceryRepository
) : ObserveItemsUseCase {

    override suspend fun invoke() {
        TODO("Not yet implemented")
    }
}

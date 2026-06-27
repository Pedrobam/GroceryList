package br.com.bamtech.grocerylist.domain.usecase

import br.com.bamtech.grocerylist.domain.model.GroceryItem
import br.com.bamtech.grocerylist.domain.repository.GroceryRepository

class AddItemUseCaseImpl(
    private val repository: GroceryRepository
) : AddItemUseCase {

    override suspend fun invoke(name: String) {
        val item = GroceryItem(
            id = 0L,
            name = name,
            isPurchased = false
        )
        repository.addItem(item)
    }
}
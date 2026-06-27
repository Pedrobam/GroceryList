package br.com.bamtech.grocerylist.domain.usecase

import br.com.bamtech.grocerylist.domain.model.GroceryItem
import br.com.bamtech.grocerylist.domain.repository.GroceryRepository

class AddItemUseCase(
    private val repository: GroceryRepository
) {

    suspend fun invoke(name: String) {
        val item = GroceryItem(name = name)
        repository.addItem(item)
    }
}
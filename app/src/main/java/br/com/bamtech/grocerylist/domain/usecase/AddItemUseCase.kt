package br.com.bamtech.grocerylist.domain.usecase

import br.com.bamtech.grocerylist.domain.model.GroceryItem
import br.com.bamtech.grocerylist.domain.repository.GroceryRepository
import javax.inject.Inject

class AddItemUseCase @Inject constructor(
    private val repository: GroceryRepository
) {

    suspend operator fun invoke(name: String) {
        val item = GroceryItem(name = name)
        repository.addItem(item)
    }
}
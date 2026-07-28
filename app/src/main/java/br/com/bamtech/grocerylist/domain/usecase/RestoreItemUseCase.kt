package br.com.bamtech.grocerylist.domain.usecase

import br.com.bamtech.grocerylist.domain.model.GroceryItem
import br.com.bamtech.grocerylist.domain.repository.GroceryRepository
import javax.inject.Inject

class RestoreItemUseCase @Inject constructor(
    private val repository: GroceryRepository,
) {

    suspend operator fun invoke(item: GroceryItem) {
        repository.restoreItem(item)
    }
}

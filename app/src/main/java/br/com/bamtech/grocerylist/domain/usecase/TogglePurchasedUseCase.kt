package br.com.bamtech.grocerylist.domain.usecase

import br.com.bamtech.grocerylist.domain.repository.GroceryRepository
import javax.inject.Inject

class TogglePurchasedUseCase @Inject constructor(
    private val repository: GroceryRepository
) {

    suspend operator fun invoke(id: Long, name: String) {
        repository.updateItemName(id, name)
    }
}
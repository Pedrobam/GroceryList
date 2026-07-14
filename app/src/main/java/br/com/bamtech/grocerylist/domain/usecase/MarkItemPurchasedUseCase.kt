package br.com.bamtech.grocerylist.domain.usecase

import br.com.bamtech.grocerylist.domain.repository.GroceryRepository
import javax.inject.Inject

class MarkItemPurchasedUseCase @Inject constructor(
    private val repository: GroceryRepository
) {

    suspend fun invoke(id: Long) {
        repository.togglePurchased(id)
    }
}
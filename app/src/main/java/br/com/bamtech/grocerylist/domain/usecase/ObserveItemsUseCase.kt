package br.com.bamtech.grocerylist.domain.usecase

import br.com.bamtech.grocerylist.domain.model.GroceryItem
import kotlinx.coroutines.flow.Flow

interface ObserveItemsUseCase {

    suspend operator fun invoke(): Flow<List<GroceryItem>>
}
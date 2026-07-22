package br.com.bamtech.grocerylist.presentation.grocery

import br.com.bamtech.grocerylist.domain.model.GroceryItem

sealed interface GroceryUiState {
    data object Loading: GroceryUiState
    data class Success(val items: List<GroceryItem>): GroceryUiState
    data class Error(val message: String): GroceryUiState
}
package br.com.bamtech.grocerylist.presentation.grocery

import br.com.bamtech.grocerylist.domain.model.GroceryItem

sealed interface GroceryUiEvent {
    data class ShowDeleteSnackbar(val item: GroceryItem) : GroceryUiEvent
}

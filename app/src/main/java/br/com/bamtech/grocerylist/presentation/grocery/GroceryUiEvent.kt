package br.com.bamtech.grocerylist.presentation.grocery

sealed interface GroceryUiEvent {
    data class ShowDeleteSnackbar(val itemName: String) : GroceryUiEvent
}

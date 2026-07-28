package br.com.bamtech.grocerylist.presentation.grocery

sealed interface GroceryUiEvent {
    data class ShowDeleteSnackBar(val itemName: String) : GroceryUiEvent
}

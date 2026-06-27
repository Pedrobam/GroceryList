package br.com.bamtech.grocerylist.domain.model

data class GroceryItem(
    val id: Long = 0L,
    val name: String,
    val isPurchased: Boolean = false,
)
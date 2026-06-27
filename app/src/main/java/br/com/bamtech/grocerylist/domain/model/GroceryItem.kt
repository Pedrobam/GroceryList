package br.com.bamtech.grocerylist.domain.model

data class GroceryItem(
    val id: Long,
    val name: String,
    val isPurchased: Boolean,
)
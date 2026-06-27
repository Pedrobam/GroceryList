package br.com.bamtech.grocerylist.data.mapper

import br.com.bamtech.grocerylist.data.local.GroceryEntity
import br.com.bamtech.grocerylist.domain.model.GroceryItem

fun GroceryEntity.toDomain(): GroceryItem {
    return GroceryItem(
        id = id,
        name = name,
        isPurchased = isPurchased
    )
}

fun GroceryItem.toEntity(): GroceryEntity {
    return GroceryEntity(
        id = id,
        name = name,
        isPurchased = isPurchased
    )
}

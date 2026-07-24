package br.com.bamtech.grocerylist.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.bamtech.grocerylist.domain.model.GroceryItem

@Composable
fun GroceryItemRow(
    item: GroceryItem,
    onPurchaseChange: () -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(
            checked = item.isPurchased,
            onCheckedChange = { onPurchaseChange() }
        )
        Text(
            item.name,
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun GroceryItemRowPreview() {
    GroceryItemRow(
        item = GroceryItem(
            id = 1,
            name = "Bananas",
            isPurchased = true
        ),
        onPurchaseChange = {}
    )
}
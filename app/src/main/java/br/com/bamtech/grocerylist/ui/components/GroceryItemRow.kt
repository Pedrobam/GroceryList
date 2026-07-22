package br.com.bamtech.grocerylist.ui.components

import android.widget.CheckBox
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
    modifier: Modifier = Modifier
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(item.name)
        Checkbox(
            checked = item.isPurchased,
            onCheckedChange = {}
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
        )
    )
}
package br.com.bamtech.grocerylist.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.bamtech.grocerylist.ui.theme.GroceryListTheme

@Composable
fun AddGroceryItemInput(
    value: String,
    onValueChange: (String) -> Unit,
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = {
                Text("Add grocery item")
            },
            modifier = Modifier.weight(1f)
        )
        Button(
            onClick = onAddClick,
            enabled = value.isNotBlank()
        ) {
            Text("Add")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AddGroceryItemInputEnablePreview() {
    GroceryListTheme {
        AddGroceryItemInput(
            value = "banana",
            onValueChange = {},
            onAddClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AddGroceryItemInputDisablePreview() {
    GroceryListTheme {
        AddGroceryItemInput(
            value = "",
            onValueChange = {},
            onAddClick = {}
        )
    }
}
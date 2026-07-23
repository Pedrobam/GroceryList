package br.com.bamtech.grocerylist.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.bamtech.grocerylist.ui.theme.GroceryListTheme

@Composable
fun EmptyContent(
    modifier: Modifier = Modifier
) {
    Text(
        text = "The list is empty.",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun EmptyContentPreview() {
    GroceryListTheme {
        EmptyContent()
    }
}
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
        modifier = modifier,
        text = "The list is empty."
    )
}

@Preview(showBackground = true)
@Composable
private fun EmptyContentPreview() {
    GroceryListTheme {
        EmptyContent()
    }
}
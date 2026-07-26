package br.com.bamtech.grocerylist.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.bamtech.grocerylist.ui.theme.GroceryListTheme
import br.com.bamtech.grocerylist.ui.theme.spacing

@Composable
fun EmptyContent(
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Icon(
            imageVector = Icons.Default.ShoppingCart,
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = null
        )

        Spacer(
            modifier = Modifier.height(
                MaterialTheme.spacing.medium
            )
        )

        Text(
            text = "Tap the + button to add your first item.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun EmptyContentPreview() {
    GroceryListTheme {
        Surface {
            EmptyContent(
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

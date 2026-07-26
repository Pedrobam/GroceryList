package br.com.bamtech.grocerylist.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.bamtech.grocerylist.domain.model.GroceryItem
import br.com.bamtech.grocerylist.ui.theme.GroceryListTheme
import br.com.bamtech.grocerylist.ui.theme.spacing

@Composable
fun GroceryItemRow(
    item: GroceryItem,
    onPurchasedChange: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val swipeState = rememberSwipeToDismissBoxState(
        confirmValueChange = { value ->
            if (value == SwipeToDismissBoxValue.EndToStart) {
                onDeleteClick()
                false
            } else false
        }
    )

    SwipeToDismissBox(
        state = swipeState,
        modifier = modifier.fillMaxWidth(),
        enableDismissFromStartToEnd = false,
        backgroundContent = {
            SwipeDeleteBackground(
                dismissDirection = swipeState.dismissDirection,
            )
        }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Checkbox(
                checked = item.isPurchased,
                onCheckedChange = { onPurchasedChange() },
            )
            Text(
                item.name,
                modifier = Modifier.weight(1f),
            )
            IconButton(
                onClick = onDeleteClick,
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete ${item.name}",
                )
            }
        }
    }
}

@Composable
private fun SwipeDeleteBackground(
    dismissDirection: SwipeToDismissBoxValue,
    modifier: Modifier = Modifier
) {
    val showDeleteAction = dismissDirection == SwipeToDismissBoxValue.EndToStart

    if (showDeleteAction) {
        Row(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.errorContainer)
                .padding(
                    horizontal = MaterialTheme.spacing.medium
                ),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onErrorContainer,
            )

            Spacer(
                modifier = Modifier.width(
                    MaterialTheme.spacing.small,
                ),
            )

            Text(
                text = "Delete",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onErrorContainer,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SwipeDeleteBackgroundPreview() {
    GroceryListTheme {
        SwipeDeleteBackground(
            dismissDirection = SwipeToDismissBoxValue.EndToStart
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun GroceryItemRowPreview() {
    GroceryListTheme {
        GroceryItemRow(
            item = GroceryItem(
                id = 1,
                name = "Bananas",
                isPurchased = true
            ),
            onPurchasedChange = {},
            onDeleteClick = {}
        )
    }
}

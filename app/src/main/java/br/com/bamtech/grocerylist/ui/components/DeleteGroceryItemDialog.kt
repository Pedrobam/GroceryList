package br.com.bamtech.grocerylist.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.bamtech.grocerylist.ui.theme.GroceryListTheme

@Composable
fun DeleteGroceryItemDialog(
    itemName: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismiss,
        title = {
            Text("Delete item?")
        },
        confirmButton = {
            TextButton(
                onClick = onConfirm
            ) {
                Text("Delete")
            }
            },
        dismissButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text("Cancel")
            }
        },
        text = {
            Text("Are you sure you want to delete \"$itemName\"?")
        }
    )
}

@Preview
@Composable
private fun DeleteGroceryItemDialogPreview() {
    GroceryListTheme {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            DeleteGroceryItemDialog(
                itemName = "Bananas",
                onConfirm = {},
                onDismiss = {}
            )
        }
    }
}
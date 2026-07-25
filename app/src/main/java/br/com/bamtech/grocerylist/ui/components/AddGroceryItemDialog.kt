package br.com.bamtech.grocerylist.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.bamtech.grocerylist.ui.theme.GroceryListTheme

@Composable
fun AddGroceryItemDialog(
    value: String,
    onValueChange: (String) -> Unit,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismiss,
        title = {
            Text("Add grocery item")
        },
        confirmButton = {
            TextButton(
                enabled = value.isNotBlank(),
                onClick = onConfirm
            ) {
                Text("Confirm")
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
            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                label = {
                    Text("Add grocery item")
                }
            )
        }
    )
}

@Preview
@Composable
private fun AddGroceryItemDialogPreview() {
    GroceryListTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            AddGroceryItemDialog(
                value = "",
                onValueChange = {},
                onConfirm = {},
                onDismiss = {}
            )
        }
    }
}

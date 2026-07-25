package br.com.bamtech.grocerylist.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import br.com.bamtech.grocerylist.ui.theme.GroceryListTheme

@Composable
fun AddGroceryItemDialog(
    value: String,
    onValueChange: (String) -> Unit,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {

    AlertDialog(
        title = {
            Text("Add grocery item")
        },
        confirmButton = {
            TextButton(
                enabled = value.isNotBlank(),
                onClick = {
                    onConfirm()
                }
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
        onDismissRequest = onDismiss,
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

@Preview(showBackground = true)
@Composable
private fun AddGroceryItemDialogPreview() {
    GroceryListTheme {
        AddGroceryItemDialog(
            value = "",
            onValueChange = {},
            onConfirm = {},
            onDismiss = {}
        )
    }
}
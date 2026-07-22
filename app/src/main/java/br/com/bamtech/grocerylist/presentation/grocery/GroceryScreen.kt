package br.com.bamtech.grocerylist.presentation.grocery

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.bamtech.grocerylist.domain.model.GroceryItem
import br.com.bamtech.grocerylist.ui.components.GroceryItemRow
import br.com.bamtech.grocerylist.ui.theme.GroceryListTheme

@Composable
fun GroceryRoute(
    viewModel: GroceryViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    GroceryScreen(uiState = uiState)
}

@Composable
fun GroceryScreen(
    uiState: GroceryUiState,
    modifier: Modifier = Modifier
) {

    when (uiState) {
        GroceryUiState.Loading -> {
            CircularProgressIndicator()
        }
        is GroceryUiState.Success -> {
            LazyColumn(modifier = modifier) {
                items(
                    uiState.items,
                    key = { item -> item.id }
                ) { item ->
                    GroceryItemRow(item = item)
                }
            }
        }
        is GroceryUiState.Error -> {
            Text(uiState.message)
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun GroceryScreenPreview() {
    GroceryListTheme {
        GroceryScreen(
            uiState = GroceryUiState.Success(
                items = listOf(
                    GroceryItem(id = 1, name = "Milk", isPurchased = false),
                    GroceryItem(id = 2, name = "Bread", isPurchased = true),
                    GroceryItem(id = 3, name = "Eggs", isPurchased = false),
                )
            )
        )
    }
}

@Preview
@Composable
private fun GroceryScreenLoadingPreview() {
    GroceryListTheme {
        GroceryScreen(uiState = GroceryUiState.Loading)
    }
}

@Preview(showBackground = true)
@Composable
private fun GroceryScreenErrorPreview() {
    GroceryListTheme {
        GroceryScreen(
            uiState = GroceryUiState.Error(
                message = "Error loading grocery items."
            )
        )
    }
}
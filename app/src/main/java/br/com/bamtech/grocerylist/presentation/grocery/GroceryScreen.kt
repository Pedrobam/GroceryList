package br.com.bamtech.grocerylist.presentation.grocery

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.bamtech.grocerylist.domain.model.GroceryItem
import br.com.bamtech.grocerylist.ui.components.AddGroceryItemDialog
import br.com.bamtech.grocerylist.ui.components.DeleteGroceryItemDialog
import br.com.bamtech.grocerylist.ui.components.EmptyContent
import br.com.bamtech.grocerylist.ui.components.GroceryItemRow
import br.com.bamtech.grocerylist.ui.theme.GroceryListTheme
import kotlinx.coroutines.launch

@Composable
fun GroceryRoute(
    viewModel: GroceryViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    LaunchedEffect(Unit) {
        viewModel.uiEvents.collect { event ->
            when(event) {
                is GroceryUiEvent.ShowDeleteSnackbar -> {
                    val snackbarResult = snackbarHostState.showSnackbar(
                        message = "${event.itemName} deleted",
                        actionLabel = "Undo"
                    )

                    if (snackbarResult == SnackbarResult.ActionPerformed) {
                        viewModel.undoDelete()
                    }
                }
            }
        }
    }

    GroceryScreen(
        uiState = uiState,
        snackbarHostState,
        onAddItem = viewModel::addItem,
        onPurchasedChange = viewModel::togglePurchased,
        onDeleteItem = viewModel::deleteItem
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroceryScreen(
    uiState: GroceryUiState,
    snackbarHostState: SnackbarHostState,
    onAddItem: (String) -> Unit,
    onPurchasedChange: (Long) -> Unit,
    onDeleteItem: (GroceryItem) -> Unit,
    modifier: Modifier = Modifier,
) {

    var itemName by rememberSaveable { mutableStateOf("") }
    var showAddDialog by rememberSaveable {
        mutableStateOf(false)
    }


    var itemToDelete by remember {
        mutableStateOf<GroceryItem?>(null)
    }

    val scope = rememberCoroutineScope()

    itemToDelete?.let { item ->
        DeleteGroceryItemDialog(
            itemName = item.name,
            onConfirm = {
                onDeleteItem(item)
                itemToDelete = null
            },
            onDismiss = {
                itemToDelete = null
            }
        )
    }

    if (showAddDialog) {
        AddGroceryItemDialog(
            value = itemName,
            onValueChange = { itemName = it },
            onConfirm = {
                val submittedName = itemName.trim()

                onAddItem(submittedName)
                itemName = ""
                showAddDialog = false
                scope.launch {
                    snackbarHostState.showSnackbar(
                        message = "$submittedName added",
                        withDismissAction = true
                    )
                }
            },
            onDismiss = {
                itemName = ""
                showAddDialog = false
            }
        )
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text("Grocery List")
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    showAddDialog = true
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add grocery item"
                )
            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                when (uiState) {
                    GroceryUiState.Loading -> {
                        CircularProgressIndicator()
                    }
                    is GroceryUiState.Success -> {
                        if (uiState.items.isEmpty()) {
                            EmptyContent()
                        } else {
                            LazyColumn(
                                modifier = Modifier.fillMaxSize()
                            ) {
                                items(
                                    items = uiState.items,
                                    key = { item -> item.id }
                                ) { item ->
                                    GroceryItemRow(
                                        modifier = Modifier.animateItem(),
                                        item = item,
                                        onPurchasedChange = {
                                            onPurchasedChange(item.id)
                                        },
                                        onDeleteClick = {
                                            itemToDelete = item
                                        }
                                    )
                                }
                            }
                        }
                    }
                    is GroceryUiState.Error -> {
                        Text(uiState.message)
                    }
                }
            }
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
            ),
            SnackbarHostState(),
            onAddItem = {},
            onPurchasedChange = {},
            onDeleteItem = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun GroceryScreenEmptyPreview() {
    GroceryListTheme {
        GroceryScreen(
            uiState = GroceryUiState.Success(items = emptyList()),
            SnackbarHostState(),
            onAddItem = {},
            onPurchasedChange = {},
            onDeleteItem = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun GroceryScreenLoadingPreview() {
    GroceryListTheme {
        GroceryScreen(
            uiState = GroceryUiState.Loading,
            SnackbarHostState(),
            onAddItem = {},
            onPurchasedChange = {},
            onDeleteItem = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun GroceryScreenErrorPreview() {
    GroceryListTheme {
        GroceryScreen(
            uiState = GroceryUiState.Error(
                message = "Error loading grocery items."
            ),
            SnackbarHostState(),
            onAddItem = {},
            onPurchasedChange = {},
            onDeleteItem = {}
        )
    }
}

package br.com.bamtech.grocerylist.presentation.grocery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.bamtech.grocerylist.domain.model.GroceryItem
import br.com.bamtech.grocerylist.domain.usecase.AddItemUseCase
import br.com.bamtech.grocerylist.domain.usecase.DeleteItemUseCase
import br.com.bamtech.grocerylist.domain.usecase.MarkItemPurchasedUseCase
import br.com.bamtech.grocerylist.domain.usecase.ObserveItemsUseCase
import br.com.bamtech.grocerylist.domain.usecase.RestoreItemUseCase
import br.com.bamtech.grocerylist.domain.usecase.TogglePurchasedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroceryViewModel @Inject constructor(
    private val observeItemsUseCase: ObserveItemsUseCase,
    private val addItemUseCase: AddItemUseCase,
    private val togglePurchasedUseCase: TogglePurchasedUseCase,
    private val deleteItemUseCase: DeleteItemUseCase,
    private val markItemPurchasedUseCase: MarkItemPurchasedUseCase,
    private val restoreItemUseCase: RestoreItemUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<GroceryUiState>(GroceryUiState.Loading)
    val uiState: StateFlow<GroceryUiState> = _uiState.asStateFlow()

    private val _uiEvents = MutableSharedFlow<GroceryUiEvent>()
    val uiEvents = _uiEvents.asSharedFlow()

    init {
        observeItems()
    }

    private fun observeItems() {
        viewModelScope.launch {
            observeItemsUseCase()
                .catch { throwable ->
                    _uiState.value = GroceryUiState.Error(
                        message = "Unable to load grocery items."
                    )
                }
                .collect { items ->
                    _uiState.value = GroceryUiState.Success(items)
                }
        }
    }

    fun addItem(name: String) {
        val normalizedName = name.trim()

        if (normalizedName.isBlank()) {
            return
        }

        viewModelScope.launch {
            addItemUseCase(normalizedName)
        }
    }

    fun updateItemName(id: Long, name: String) {
        val normalizedName = name.trim()

        if (normalizedName.isBlank()) {
            return
        }

        viewModelScope.launch {
            togglePurchasedUseCase(id, normalizedName)
        }
    }

    fun deleteItem(item: GroceryItem) {
        viewModelScope.launch {
            deleteItemUseCase(item.id)

            _uiEvents.emit(
                GroceryUiEvent.ShowDeleteSnackbar(item)
            )
        }
    }

    fun undoDelete(item: GroceryItem) {
        viewModelScope.launch {
            restoreItemUseCase(item)
        }
    }

    fun togglePurchased(id: Long) {
        viewModelScope.launch {
            markItemPurchasedUseCase(id)
        }
    }
}

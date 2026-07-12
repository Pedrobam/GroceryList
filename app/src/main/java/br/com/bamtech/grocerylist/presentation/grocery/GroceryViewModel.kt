package br.com.bamtech.grocerylist.presentation.grocery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.bamtech.grocerylist.domain.model.GroceryItem
import br.com.bamtech.grocerylist.domain.repository.GroceryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroceryViewModel @Inject constructor(
    private val repository: GroceryRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<GroceryUiState>(GroceryUiState.Loading)
    val uiState: StateFlow<GroceryUiState> = _uiState.asStateFlow()

    init {
        observeItems()
    }

    private fun observeItems() {
        viewModelScope.launch {
            repository.observeItems()
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
            repository.addItem(normalizedName)
        }
    }

    fun updateItemName(id: Long, name: String) {
        val normalizedName = name.trim()

        if (normalizedName.isBlank()) {
            return
        }

        viewModelScope.launch {
            repository.updateItemName(id, normalizedName)
        }
    }

    fun deleteItem(id: Long) {
        viewModelScope.launch {
            repository.deleteItem(id)
        }
    }

    fun togglePurchased(id: Long) {
        viewModelScope.launch {
            repository.togglePurchased(id)
        }
    }
}
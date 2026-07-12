package br.com.bamtech.grocerylist.presentation.grocery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.bamtech.grocerylist.domain.repository.GroceryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroceryViewModel @Inject constructor(
    private val repository: GroceryRepository
): ViewModel() {

    private val _uiState = MutableStateFlow<GroceryUiState>(GroceryUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch { 
            repository.observeItems().collect { items ->
                _uiState.emit(GroceryUiState.Success(items))
            }
        }
    }

    fun onItemNameChanged(value: String) {

    }

    fun addItem() {

    }

    fun deleteItem(id: Long) {

    }

    fun togglePurchased(id: Long) {

    }
}
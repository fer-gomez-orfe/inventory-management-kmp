package org.montra.crudmuliplatform.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.montra.crudmuliplatform.data.model.SparePartModel
import org.montra.crudmuliplatform.data.repository.SparePartsRepository


@OptIn(FlowPreview::class)
class SparePartsCatalogViewModel(
    private val repository: SparePartsRepository
): ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _spareParts = MutableStateFlow<List<SparePartModel>>(emptyList())
    val spareParts = searchText
        .debounce(1000L)
        .onEach { _isSearching.update { true } }
        .combine(_spareParts) { text, sparePart ->
            if(text.isBlank()) {
                sparePart
            } else {
                //delay(2000L)
                sparePart.filter {
                    it.doesMatchSearchQuery(text)

                }
            }
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _spareParts.value
        )

    init {
        loadSpareParts()
    }

    private fun loadSpareParts() {
        viewModelScope.launch {
            try {
                val parts = repository.fetchSpareParts()
                _spareParts.value = parts
            } catch (e: Exception) {
                // Manejo de errores
                Logger.e(e) { "Error al cargar datos: ${e.message}" }

            }
        }
    }

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

}




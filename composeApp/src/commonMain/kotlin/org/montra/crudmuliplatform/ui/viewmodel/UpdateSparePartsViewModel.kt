package org.montra.crudmuliplatform.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.montra.crudmuliplatform.data.model.SparePartModel
import org.montra.crudmuliplatform.data.repository.SparePartsRepository

@OptIn(FlowPreview::class)
class UpdateSparePartsViewModel (
    private val repository: SparePartsRepository
) : ViewModel() {

    private val _searchText = MutableStateFlow ("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _spareParts = MutableStateFlow<List<SparePartModel>>(emptyList())

    private val _filteredSpareParts = MutableStateFlow<List<SparePartModel>>(emptyList())
    val filteredSpareParts = _filteredSpareParts.asStateFlow()

    private val _validation = MutableStateFlow(false)
    val validation = _validation.asStateFlow()

    private val _nombreProducto = MutableStateFlow("")
    val nombreProducto = _nombreProducto.asStateFlow()

    private val _productName = MutableStateFlow("")
    val productName = _productName.asStateFlow()

    private val _description = MutableStateFlow("")
    val description = _description.asStateFlow()

    private val _partNumber = MutableStateFlow("")
    val partNumber = _partNumber.asStateFlow()

    private val _quantity = MutableStateFlow("")
    val quantity = _quantity.asStateFlow()

    private val _notes = MutableStateFlow("")
    val notes = _notes.asStateFlow()


    fun onSearchTextChange(text : String) {
        _searchText.value = text
    }

    fun onNombreProductoTextChange(text : String) {
        _nombreProducto.value = text
    }

    fun onProductNameTextChange(text : String) {
        _productName.value = text
    }

    fun onDescriptionTextChange(text : String) {
        _description.value = text
    }

    fun onPartNumberTextChange(text : String) {
        _partNumber.value = text
    }

    fun onQuantityTextChange(text: String) {
        _quantity.value = text
    }

    fun onNotesTextChange(text : String) {
        _notes.value = text
    }



    init {
        loadSpareParts()
    }

    fun onButtonSearchClick(){
        viewModelScope.launch {
            _isSearching.value = true
            delay(500L)

            val text = _searchText.value
            val parts = _spareParts.value

            _filteredSpareParts.value = if(text.isBlank()){
                emptyList<SparePartModel>()
            }else{
                parts.filter {
                    it.checkIfExist(text)
                }
            }

            _validation.value = if (_filteredSpareParts.value.isEmpty()) true else false

            _isSearching.value = false
        }
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



}
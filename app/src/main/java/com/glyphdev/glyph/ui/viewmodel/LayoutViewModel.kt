package com.glyphdev.glyph.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.glyphdev.glyph.data.models.Layout
import com.glyphdev.glyph.data.models.Widget
import com.glyphdev.glyph.data.repository.PreferencesRepository
import com.glyphdev.glyph.domain.usecase.AddWidgetUseCase
import com.glyphdev.glyph.domain.usecase.CreateLayoutUseCase
import com.glyphdev.glyph.domain.usecase.RemoveWidgetUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LayoutViewModel(
    private val repository: PreferencesRepository,
    private val createLayoutUseCase: CreateLayoutUseCase,
    private val addWidgetUseCase: AddWidgetUseCase,
    private val removeWidgetUseCase: RemoveWidgetUseCase
) : ViewModel() {
    
    private val _currentLayout = MutableStateFlow<Layout?>(null)
    val currentLayout: StateFlow<Layout?> = _currentLayout.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    init {
        loadCurrentLayout()
    }
    
    private fun loadCurrentLayout() {
        viewModelScope.launch {
            repository.currentLayout.collect { layout ->
                _currentLayout.value = layout
            }
        }
    }
    
    fun createNewLayout(name: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val layout = createLayoutUseCase(name)
                _currentLayout.value = layout
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    fun addWidget(widget: Widget) {
        viewModelScope.launch {
            _currentLayout.value?.let { layout ->
                val updatedLayout = addWidgetUseCase(layout, widget)
                _currentLayout.value = updatedLayout
            }
        }
    }
    
    fun removeWidget(widgetId: String) {
        viewModelScope.launch {
            _currentLayout.value?.let { layout ->
                val updatedLayout = removeWidgetUseCase(layout, widgetId)
                _currentLayout.value = updatedLayout
            }
        }
    }
}

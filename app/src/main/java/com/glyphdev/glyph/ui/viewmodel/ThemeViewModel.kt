package com.glyphdev.glyph.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.glyphdev.glyph.data.models.Theme
import com.glyphdev.glyph.data.repository.PreferencesRepository
import com.glyphdev.glyph.domain.usecase.GetThemesUseCase
import com.glyphdev.glyph.domain.usecase.SaveThemeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ThemeViewModel(
    private val repository: PreferencesRepository,
    private val getThemesUseCase: GetThemesUseCase,
    private val saveThemeUseCase: SaveThemeUseCase
) : ViewModel() {
    
    private val _currentTheme = MutableStateFlow<Theme?>(null)
    val currentTheme: StateFlow<Theme?> = _currentTheme.asStateFlow()
    
    private val _availableThemes = MutableStateFlow<List<Theme>>(emptyList())
    val availableThemes: StateFlow<List<Theme>> = _availableThemes.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    init {
        loadThemes()
        loadCurrentTheme()
    }
    
    private fun loadThemes() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val themes = getThemesUseCase()
                _availableThemes.value = themes
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    private fun loadCurrentTheme() {
        viewModelScope.launch {
            repository.currentTheme.collect { theme ->
                _currentTheme.value = theme
            }
        }
    }
    
    fun selectTheme(theme: Theme) {
        viewModelScope.launch {
            saveThemeUseCase(theme)
            _currentTheme.value = theme
        }
    }
}

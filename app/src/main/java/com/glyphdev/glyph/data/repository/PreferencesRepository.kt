package com.glyphdev.glyph.data.repository

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.glyphdev.glyph.data.models.Layout
import com.glyphdev.glyph.data.models.Theme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json

private val Context.dataStore by preferencesDataStore(name = "glyph_preferences")

class PreferencesRepository(private val context: Context) {
    private val json = Json { ignoreUnknownKeys = true }
    
    private val CURRENT_LAYOUT = stringPreferencesKey("current_layout")
    private val CURRENT_THEME = stringPreferencesKey("current_theme")
    private val LAYOUTS = stringPreferencesKey("layouts")
    private val THEMES = stringPreferencesKey("themes")
    
    val currentLayout: Flow<Layout?> = context.dataStore.data.map { preferences ->
        preferences[CURRENT_LAYOUT]?.let {
            json.decodeFromString<Layout>(it)
        }
    }
    
    val currentTheme: Flow<Theme?> = context.dataStore.data.map { preferences ->
        preferences[CURRENT_THEME]?.let {
            json.decodeFromString<Theme>(it)
        }
    }
    
    suspend fun saveLayout(layout: Layout) {
        context.dataStore.edit { preferences ->
            preferences[CURRENT_LAYOUT] = json.encodeToString(Layout.serializer(), layout)
        }
    }
    
    suspend fun saveTheme(theme: Theme) {
        context.dataStore.edit { preferences ->
            preferences[CURRENT_THEME] = json.encodeToString(Theme.serializer(), theme)
        }
    }
    
    suspend fun clearData() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}

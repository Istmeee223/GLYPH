package com.glyphdev.glyph.domain.usecase

import com.glyphdev.glyph.data.models.Theme
import com.glyphdev.glyph.data.repository.PreferencesRepository

class GetThemesUseCase(private val repository: PreferencesRepository) {
    suspend operator fun invoke(): List<Theme> {
        return listOf(
            Theme(
                id = "monochrome",
                name = "Monochrome",
                primaryColor = "#00FF00",
                secondaryColor = "#00FFFF",
                accentColor = "#FF00FF",
                backgroundColor = "#000000"
            ),
            Theme(
                id = "neon_edge",
                name = "Neon Edge",
                primaryColor = "#FF0080",
                secondaryColor = "#00FFFF",
                accentColor = "#FFFF00",
                backgroundColor = "#0A0E27"
            ),
            Theme(
                id = "minimal",
                name = "Minimal",
                primaryColor = "#FFFFFF",
                secondaryColor = "#666666",
                accentColor = "#333333",
                backgroundColor = "#FAFAFA"
            )
        )
    }
}

class SaveThemeUseCase(private val repository: PreferencesRepository) {
    suspend operator fun invoke(theme: Theme) {
        repository.saveTheme(theme)
    }
}

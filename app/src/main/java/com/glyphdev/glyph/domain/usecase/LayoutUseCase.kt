package com.glyphdev.glyph.domain.usecase

import com.glyphdev.glyph.data.models.Layout
import com.glyphdev.glyph.data.models.Widget
import com.glyphdev.glyph.data.models.WidgetConfig
import com.glyphdev.glyph.data.models.WidgetType
import com.glyphdev.glyph.data.repository.PreferencesRepository
import java.util.UUID

class CreateLayoutUseCase(private val repository: PreferencesRepository) {
    suspend operator fun invoke(name: String): Layout {
        val layout = Layout(
            id = UUID.randomUUID().toString(),
            name = name,
            widgets = emptyList()
        )
        repository.saveLayout(layout)
        return layout
    }
}

class AddWidgetUseCase(private val repository: PreferencesRepository) {
    suspend operator fun invoke(layout: Layout, widget: Widget): Layout {
        val updatedLayout = layout.copy(
            widgets = layout.widgets + widget,
            updatedAt = System.currentTimeMillis()
        )
        repository.saveLayout(updatedLayout)
        return updatedLayout
    }
}

class RemoveWidgetUseCase(private val repository: PreferencesRepository) {
    suspend operator fun invoke(layout: Layout, widgetId: String): Layout {
        val updatedLayout = layout.copy(
            widgets = layout.widgets.filter { it.id != widgetId },
            updatedAt = System.currentTimeMillis()
        )
        repository.saveLayout(updatedLayout)
        return updatedLayout
    }
}

class CreateSampleWidgets {
    operator fun invoke(): List<Widget> {
        return listOf(
            Widget(
                id = UUID.randomUUID().toString(),
                type = WidgetType.CLOCK,
                x = 0, y = 0,
                width = 3, height = 3,
                config = WidgetConfig(clockStyle = "dotMatrix")
            ),
            Widget(
                id = UUID.randomUUID().toString(),
                type = WidgetType.VISUALIZER,
                x = 3, y = 0,
                width = 3, height = 3,
                config = WidgetConfig(visualizerType = "waveform")
            ),
            Widget(
                id = UUID.randomUUID().toString(),
                type = WidgetType.BATTERY,
                x = 0, y = 3,
                width = 2, height = 1,
                config = WidgetConfig()
            )
        )
    }
}

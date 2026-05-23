package com.glyphdev.glyph.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Widget(
    val id: String,
    val type: WidgetType,
    val x: Int,
    val y: Int,
    val width: Int,
    val height: Int,
    val config: WidgetConfig
)

@Serializable
data class WidgetConfig(
    val clockStyle: String = "dotMatrix",
    val visualizerType: String = "waveform",
    val color: String = "#00FF00",
    val opacity: Float = 1f,
    val animationSpeed: Float = 1f
)

enum class WidgetType {
    CLOCK, VISUALIZER, BATTERY, WEATHER, MUSIC, CUSTOM
}

@Serializable
data class Layout(
    val id: String,
    val name: String,
    val widgets: List<Widget>,
    val gridSize: Int = 6,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

@Serializable
data class Theme(
    val id: String,
    val name: String,
    val primaryColor: String,
    val secondaryColor: String,
    val accentColor: String,
    val backgroundColor: String,
    val isDark: Boolean = true,
    val fontFamily: String = "Inter"
)

@Serializable
data class IconPack(
    val id: String,
    val name: String,
    val description: String,
    val icons: Map<String, String>,
    val monochrome: Boolean = true
)

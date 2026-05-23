package com.glyphdev.glyph.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.glyphdev.glyph.ui.components.*
import com.glyphdev.glyph.ui.theme.GlyphSpacing

@Composable
fun SettingsScreen(
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0x000000))
            .padding(GlyphSpacing.xl)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Settings",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFFFF)
            )
            
            GlyphButton(
                text = "×",
                onClick = onBack,
                size = ButtonSize.Small
            )
        }
        
        Spacer(modifier = Modifier.height(GlyphSpacing.xl))
        
        // Display
        SettingSection(
            title = "Display",
            settings = listOf(
                "Dark Mode" to "Enabled",
                "OLED Optimization" to "On",
                "Always-On Display" to "Off"
            )
        )
        
        Spacer(modifier = Modifier.height(GlyphSpacing.xl))
        
        // Animation
        SettingSection(
            title = "Animation",
            settings = listOf(
                "Motion Effect" to "High",
                "Transition Speed" to "Normal",
                "Haptic Feedback" to "Enabled"
            )
        )
        
        Spacer(modifier = Modifier.height(GlyphSpacing.xl))
        
        // Audio
        SettingSection(
            title = "Audio",
            settings = listOf(
                "Visualizer Sensitivity" to "High",
                "Reactive Widgets" to "Enabled",
                "Bass Boost" to "Off"
            )
        )
        
        Spacer(modifier = Modifier.height(GlyphSpacing.xl))
        
        // About
        SettingSection(
            title = "About",
            settings = listOf(
                "Version" to "1.0.0",
                "Build" to "2026.05",
                "Developer" to "GLYPH Dev"
            )
        )
        
        Spacer(modifier = Modifier.height(GlyphSpacing.xl))
        
        GlyphButton(
            text = "Reset to Defaults",
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
            variant = ButtonVariant.Secondary
        )
    }
}

@Composable
fun SettingSection(
    title: String,
    settings: List<Pair<String, String>>
) {
    Column {
        Text(
            text = title,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0x999999)
        )
        
        Spacer(modifier = Modifier.height(GlyphSpacing.lg))
        
        GlyphCard(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                settings.forEachIndexed { index, (label, value) ->
                    SettingRow(label = label, value = value)
                    
                    if (index < settings.size - 1) {
                        GlyphDivider(modifier = Modifier.padding(vertical = GlyphSpacing.md))
                    }
                }
            }
        }
    }
}

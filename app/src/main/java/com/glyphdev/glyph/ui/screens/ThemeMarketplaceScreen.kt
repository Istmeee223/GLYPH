package com.glyphdev.glyph.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.glyphdev.glyph.data.models.Theme
import com.glyphdev.glyph.ui.components.*
import com.glyphdev.glyph.ui.theme.GlyphSpacing

@Composable
fun ThemeMarketplaceScreen(
    onSelectTheme: (Theme) -> Unit
) {
    val themes = listOf(
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
        ),
        Theme(
            id = "cyberpunk",
            name = "Cyberpunk",
            primaryColor = "#FF006E",
            secondaryColor = "#00D4FF",
            accentColor = "#FFBE0B",
            backgroundColor = "#0F0F1E"
        )
    )
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0x000000))
            .padding(GlyphSpacing.xl)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Themes",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFFFFF)
        )
        
        Spacer(modifier = Modifier.height(GlyphSpacing.lg))
        
        Text(
            text = "Choose your visual aesthetic",
            fontSize = 14.sp,
            color = Color(0x999999)
        )
        
        Spacer(modifier = Modifier.height(GlyphSpacing.xxl))
        
        themes.forEach { theme ->
            ThemeCard(
                theme = theme,
                onClick = { onSelectTheme(theme) }
            )
            
            Spacer(modifier = Modifier.height(GlyphSpacing.lg))
        }
    }
}

@Composable
fun ThemeCard(
    theme: Theme,
    onClick: () -> Unit
) {
    GlyphCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = theme.name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFFFFF)
                    )
                    
                    Spacer(modifier = Modifier.height(GlyphSpacing.sm))
                    
                    Text(
                        text = "Custom ${theme.fontFamily} font",
                        fontSize = 12.sp,
                        color = Color(0x999999)
                    )
                }
                
                GlyphBadge(text = "Select")
            }
            
            Spacer(modifier = Modifier.height(GlyphSpacing.lg))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(GlyphSpacing.md)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(40.dp)
                        .background(
                            color = Color(android.graphics.Color.parseColor(theme.primaryColor))
                        )
                )
                
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(40.dp)
                        .background(
                            color = Color(android.graphics.Color.parseColor(theme.secondaryColor))
                        )
                )
                
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(40.dp)
                        .background(
                            color = Color(android.graphics.Color.parseColor(theme.accentColor))
                        )
                )
            }
        }
    }
}

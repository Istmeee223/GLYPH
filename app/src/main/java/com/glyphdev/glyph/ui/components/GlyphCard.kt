package com.glyphdev.glyph.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.glyphdev.glyph.ui.theme.GlyphRadius
import com.glyphdev.glyph.ui.theme.GlyphSpacing
import com.glyphdev.glyph.ui.theme.JetBrainsMonoFont

@Composable
fun GlyphCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(GlyphRadius.md))
            .background(Color(0x111111))
            .border(
                width = 1.dp,
                color = Color(0x2D2D2D),
                shape = RoundedCornerShape(GlyphRadius.md)
            )
            .padding(GlyphSpacing.lg)
    ) {
        content()
    }
}

@Composable
fun GlyphDivider(
    modifier: Modifier = Modifier,
    color: Color = Color(0x2D2D2D),
    thickness: Float = 1f
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(thickness.dp)
            .background(color)
    )
}

@Composable
fun GlyphBadge(
    text: String,
    modifier: Modifier = Modifier,
    variant: BadgeVariant = BadgeVariant.Primary
) {
    val (backgroundColor, textColor) = when (variant) {
        BadgeVariant.Primary -> Color(0x00FF00) to Color(0x000000)
        BadgeVariant.Secondary -> Color(0x2D2D2D) to Color(0xFFFFFF)
        BadgeVariant.Accent -> Color(0xFF00FF) to Color(0x000000)
    }
    
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(GlyphRadius.full))
            .background(backgroundColor)
            .padding(horizontal = GlyphSpacing.md, vertical = GlyphSpacing.sm),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 11.sp,
            fontFamily = JetBrainsMonoFont,
            fontWeight = FontWeight.Bold,
            color = textColor
        )
    }
}

enum class BadgeVariant {
    Primary, Secondary, Accent
}

@Composable
fun BatteryRing(
    percentage: Float,
    modifier: Modifier = Modifier,
    size: Float = 60f
) {
    Box(
        modifier = modifier.size(size.dp),
        contentAlignment = Alignment.Center
    ) {
        // Background ring
        Box(
            modifier = Modifier
                .size(size.dp)
                .border(
                    width = 2.dp,
                    color = Color(0x2D2D2D),
                    shape = RoundedCornerShape(GlyphRadius.full)
                )
        )
        
        // Percentage text
        Text(
            text = "${(percentage * 100).toInt()}%",
            fontSize = 12.sp,
            fontFamily = JetBrainsMonoFont,
            fontWeight = FontWeight.Bold,
            color = when {
                percentage > 0.5f -> Color(0x00FF00)
                percentage > 0.2f -> Color(0xFFFF00)
                else -> Color(0xFF0000)
            }
        )
    }
}

@Composable
fun WeatherGlyph(
    temperature: Int,
    condition: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = condition,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFFFFF)
        )
        Text(
            text = "$temperature°",
            fontSize = 32.sp,
            fontFamily = JetBrainsMonoFont,
            fontWeight = FontWeight.Bold,
            color = Color(0x00FFFF)
        )
    }
}

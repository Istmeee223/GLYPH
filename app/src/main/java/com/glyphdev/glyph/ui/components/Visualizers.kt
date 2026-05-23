package com.glyphdev.glyph.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.glyphdev.glyph.ui.theme.GlyphRadius
import com.glyphdev.glyph.ui.theme.GlyphSpacing
import kotlin.math.sin

@Composable
fun WaveformVisualizer(
    modifier: Modifier = Modifier,
    barCount: Int = 20,
    color: Color = Color(0x00FF00),
    isAnimating: Boolean = true
) {
    val infiniteTransition = rememberInfiniteTransition(label = "waveform")
    
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(GlyphSpacing.md),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Bottom
    ) {
        repeat(barCount) { index ->
            val height by infiniteTransition.animateFloat(
                initialValue = 0.1f,
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = 800,
                        delayMillis = (index * 30) % 800,
                        easing = EaseInOutQuad
                    ),
                    repeatMode = RepeatMode.Reverse
                ),
                label = "bar_height_$index"
            )
            
            val animatedHeight = if (isAnimating) height else 0.5f
            
            Box(
                modifier = Modifier
                    .fillMaxHeight(animatedHeight)
                    .width(6.dp)
                    .clip(RoundedCornerShape(topStart = GlyphRadius.sm, topEnd = GlyphRadius.sm))
                    .background(color.copy(alpha = 0.6f + 0.4f * (index / barCount.toFloat())))
            )
        }
    }
}

@Composable
fun CircularSpectrumVisualizer(
    modifier: Modifier = Modifier,
    bands: Int = 12,
    color: Color = Color(0x00FFFF),
    isAnimating: Boolean = true
) {
    val infiniteTransition = rememberInfiniteTransition(label = "spectrum")
    
    Box(
        modifier = modifier
            .size(200.dp)
            .clip(RoundedCornerShape(GlyphRadius.full))
            .background(Color(0x000000))
            .border(
                width = 1.dp,
                color = Color(0x404040),
                shape = RoundedCornerShape(GlyphRadius.full)
            ),
        contentAlignment = Alignment.Center
    ) {
        repeat(bands) { index ->
            val magnitude by infiniteTransition.animateFloat(
                initialValue = 0.3f,
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = 1000,
                        delayMillis = (index * 80) % 1000
                    ),
                    repeatMode = RepeatMode.Reverse
                ),
                label = "magnitude_$index"
            )
            
            val angle = (360f / bands) * index
            val animatedHeight = if (isAnimating) magnitude else 0.5f
            
            SpectrumBar(
                angle = angle,
                height = animatedHeight,
                color = color
            )
        }
    }
}

@Composable
fun SpectrumBar(
    angle: Float,
    height: Float,
    color: Color
) {
    Box(
        modifier = Modifier
            .size(200.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Box(
            modifier = Modifier
                .width(4.dp)
                .fillMaxHeight(0.3f + 0.2f * height)
                .clip(RoundedCornerShape(2.dp))
                .background(color.copy(alpha = 0.5f + 0.5f * height))
                .graphicsLayer {
                    rotationZ = angle
                    transformOrigin = androidx.compose.ui.graphics.TransformOrigin(0.5f, 0.5f)
                }
        )
    }
}

@Composable
fun AnimatedGlyphBars(
    modifier: Modifier = Modifier,
    bars: Int = 8,
    colors: List<Color> = listOf(
        Color(0xFF00FF),
        Color(0x00FFFF),
        Color(0x00FF00)
    ),
    isAnimating: Boolean = true
) {
    val infiniteTransition = rememberInfiniteTransition(label = "glyph_bars")
    
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(GlyphSpacing.lg),
        horizontalArrangement = Arrangement.spacedBy(GlyphSpacing.sm),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(bars) { index ->
            val height by infiniteTransition.animateFloat(
                initialValue = 0.2f,
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = 600,
                        delayMillis = (index * 75) % 600
                    ),
                    repeatMode = RepeatMode.Reverse
                ),
                label = "glyph_bar_$index"
            )
            
            val animatedHeight = if (isAnimating) height else 0.5f
            val barColor = colors[index % colors.size]
            
            Box(
                modifier = Modifier
                    .fillMaxHeight(animatedHeight)
                    .weight(1f)
                    .clip(RoundedCornerShape(topStart = GlyphRadius.sm, topEnd = GlyphRadius.sm))
                    .background(barColor.copy(alpha = 0.7f))
            )
        }
    }
}

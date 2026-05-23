package com.glyphdev.glyph.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.glyphdev.glyph.ui.theme.GlyphRadius
import com.glyphdev.glyph.ui.theme.GlyphSpacing

@Composable
fun GlyphGlassCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(GlyphRadius.lg))
            .background(
                color = Color(0x1A1A1A).copy(alpha = 0.8f)
            )
            .border(
                width = 1.dp,
                color = Color(0xFFFFFF).copy(alpha = 0.1f),
                shape = RoundedCornerShape(GlyphRadius.lg)
            )
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(GlyphRadius.lg),
                spotColor = Color(0x00FF00).copy(alpha = 0.3f)
            )
            .padding(GlyphSpacing.lg)
    ) {
        content()
    }
}

@Composable
fun GlyphGlowButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    glowColor: Color = Color(0x00FF00)
) {
    val infiniteTransition = rememberInfiniteTransition(label = "glow")
    
    val glowAlpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 0.8f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500),
            repeatMode = RepeatMode.Reverse
        ),
        label = "glow_alpha"
    )
    
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(GlyphRadius.md))
            .background(glowColor.copy(alpha = 0.2f))
            .border(
                width = 2.dp,
                color = glowColor.copy(alpha = glowAlpha),
                shape = RoundedCornerShape(GlyphRadius.md)
            )
            .shadow(
                elevation = 12.dp,
                shape = RoundedCornerShape(GlyphRadius.md),
                spotColor = glowColor
            )
            .padding(GlyphSpacing.lg),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = glowColor
        )
    }
}

@Composable
fun FuturisticLoader(
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "futuristic_loader")
    
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotation"
    )
    
    Box(
        modifier = modifier.size(50.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .border(
                    width = 2.dp,
                    color = Color(0x00FF00).copy(alpha = 0.3f),
                    shape = RoundedCornerShape(GlyphRadius.full)
                )
        )
        
        Box(
            modifier = Modifier
                .size(50.dp)
                .graphicsLayer(
                    rotationZ = rotation,
                    transformOrigin = TransformOrigin(0.5f, 0.5f)
                )
                .border(
                    width = 2.dp,
                    color = Color(0x00FF00),
                    shape = RoundedCornerShape(GlyphRadius.full)
                )
        )
        
        Box(
            modifier = Modifier
                .size(12.dp)
                .clip(RoundedCornerShape(GlyphRadius.full))
                .background(Color(0x00FF00))
        )
    }
}

@Composable
fun BreathingPulse(
    modifier: Modifier = Modifier,
    color: Color = Color(0x00FF00),
    size: Float = 24f
) {
    val infiniteTransition = rememberInfiniteTransition(label = "breathing")
    
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.8f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200),
            repeatMode = RepeatMode.Reverse
        ),
        label = "breathing_scale"
    )
    
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200),
            repeatMode = RepeatMode.Reverse
        ),
        label = "breathing_alpha"
    )
    
    Box(
        modifier = modifier
            .size(size.dp)
            .graphicsLayer(
                scaleX = scale,
                scaleY = scale
            )
            .clip(RoundedCornerShape(GlyphRadius.full))
            .background(color.copy(alpha = alpha))
    )
}

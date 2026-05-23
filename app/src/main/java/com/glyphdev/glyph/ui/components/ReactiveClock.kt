package com.glyphdev.glyph.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.glyphdev.glyph.ui.theme.GlyphRadius
import com.glyphdev.glyph.ui.theme.GlyphSpacing
import com.glyphdev.glyph.ui.theme.JetBrainsMonoFont
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun ReactiveClock(modifier: Modifier = Modifier, style: ClockStyle = ClockStyle.DotMatrix) {
    var currentTime by remember { mutableStateOf(LocalDateTime.now()) }
    
    LaunchedEffect(Unit) {
        while (true) {
            kotlinx.coroutines.delay(1000)
            currentTime = LocalDateTime.now()
        }
    }
    
    when (style) {
        ClockStyle.DotMatrix -> DotMatrixClock(currentTime, modifier)
        ClockStyle.Minimal -> MinimalClock(currentTime, modifier)
        ClockStyle.Analog -> AnalogClock(currentTime, modifier)
    }
}

@Composable
fun DotMatrixClock(time: LocalDateTime, modifier: Modifier = Modifier) {
    val hours = time.format(DateTimeFormatter.ofPattern("HH"))
    val minutes = time.format(DateTimeFormatter.ofPattern("mm"))
    val seconds = time.format(DateTimeFormatter.ofPattern("ss"))
    
    Column(
        modifier = modifier
            .background(
                color = Color(0x000000),
                shape = RoundedCornerShape(GlyphRadius.md)
            )
            .padding(GlyphSpacing.lg),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            DotMatrixDigits(hours)
            Text(
                text = ":",
                fontSize = 48.sp,
                fontFamily = JetBrainsMonoFont,
                color = Color(0x00FF00),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 4.dp)
            )
            DotMatrixDigits(minutes)
        }
        
        Text(
            text = seconds,
            fontSize = 16.sp,
            fontFamily = JetBrainsMonoFont,
            color = Color(0x00FF00).copy(alpha = 0.6f),
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(top = GlyphSpacing.md)
        )
    }
}

@Composable
fun DotMatrixDigits(text: String) {
    Text(
        text = text,
        fontSize = 48.sp,
        fontFamily = JetBrainsMonoFont,
        color = Color(0x00FF00),
        fontWeight = FontWeight.Bold,
        letterSpacing = 4.sp
    )
}

@Composable
fun MinimalClock(time: LocalDateTime, modifier: Modifier = Modifier) {
    val hours = time.format(DateTimeFormatter.ofPattern("HH"))
    val minutes = time.format(DateTimeFormatter.ofPattern("mm"))
    
    val infiniteTransition = rememberInfiniteTransition(label = "minimal_clock")
    val secondGlow by infiniteTransition.animateFloat(
        initialValue = 0.4f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "second_glow"
    )
    
    Column(
        modifier = modifier
            .background(
                color = Color(0x111111),
                shape = RoundedCornerShape(GlyphRadius.lg)
            )
            .padding(GlyphSpacing.xl),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "$hours : $minutes",
            fontSize = 64.sp,
            fontFamily = JetBrainsMonoFont,
            color = Color(0xFFFFFF),
            fontWeight = FontWeight.Light,
            letterSpacing = 2.sp
        )
        
        Box(
            modifier = Modifier
                .padding(top = GlyphSpacing.md)
                .size(8.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(Color(0x00FF00).copy(alpha = secondGlow))
        )
    }
}

@Composable
fun AnalogClock(time: LocalDateTime, modifier: Modifier = Modifier) {
    val hours = time.hour % 12
    val minutes = time.minute
    val seconds = time.second
    
    val hourRotation = (hours * 30f + minutes * 0.5f)
    val minuteRotation = (minutes * 6f + seconds * 0.1f)
    val secondRotation = (seconds * 6f)
    
    Box(
        modifier = modifier
            .size(200.dp)
            .clip(RoundedCornerShape(GlyphRadius.full))
            .background(Color(0x000000))
            .border(
                width = 2.dp,
                color = Color(0x404040),
                shape = RoundedCornerShape(GlyphRadius.full)
            ),
        contentAlignment = Alignment.Center
    ) {
        // Hour hand
        ClockHand(
            rotation = hourRotation,
            length = 60.dp,
            width = 3.dp,
            color = Color(0xFFFFFF)
        )
        
        // Minute hand
        ClockHand(
            rotation = minuteRotation,
            length = 80.dp,
            width = 2.dp,
            color = Color(0x00FF00)
        )
        
        // Second hand
        ClockHand(
            rotation = secondRotation,
            length = 85.dp,
            width = 1.dp,
            color = Color(0xFF00FF).copy(alpha = 0.8f)
        )
        
        // Center dot
        Box(
            modifier = Modifier
                .size(8.dp)
                .clip(RoundedCornerShape(GlyphRadius.full))
                .background(Color(0x00FF00))
        )
    }
}

@Composable
fun ClockHand(
    rotation: Float,
    length: androidx.compose.ui.unit.Dp,
    width: androidx.compose.ui.unit.Dp,
    color: Color
) {
    Box(
        modifier = Modifier
            .size(width, length)
            .graphicsLayer(
                rotationZ = rotation,
                transformOrigin = TransformOrigin(0.5f, 1f)
            )
            .background(color)
    )
}

enum class ClockStyle {
    DotMatrix, Minimal, Analog
}

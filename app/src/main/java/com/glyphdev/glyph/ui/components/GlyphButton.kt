package com.glyphdev.glyph.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.glyphdev.glyph.ui.theme.GlyphRadius
import com.glyphdev.glyph.ui.theme.GlyphSpacing

@Composable
fun GlyphButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    variant: ButtonVariant = ButtonVariant.Primary,
    size: ButtonSize = ButtonSize.Medium,
    isLoading: Boolean = false,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    
    val alpha by animateFloatAsState(
        targetValue = if (isPressed) 0.8f else 1f,
        label = "button_alpha"
    )
    
    val (paddingHorizontal, paddingVertical, fontSize, cornerRadius) = when (size) {
        ButtonSize.Small -> Tuple4(GlyphSpacing.md, GlyphSpacing.sm, 12.sp, GlyphRadius.sm)
        ButtonSize.Medium -> Tuple4(GlyphSpacing.lg, GlyphSpacing.md, 14.sp, GlyphRadius.md)
        ButtonSize.Large -> Tuple4(GlyphSpacing.xl, GlyphSpacing.lg, 16.sp, GlyphRadius.lg)
    }
    
    val (backgroundColor, textColor) = when (variant) {
        ButtonVariant.Primary -> Color(0x00FF00) to Color(0x000000)
        ButtonVariant.Secondary -> Color(0x2D2D2D) to Color(0xFFFFFF)
        ButtonVariant.Ghost -> Color.Transparent to Color(0xFFFFFF)
    }
    
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(cornerRadius))
            .background(
                if (variant == ButtonVariant.Ghost) Color.Transparent 
                else backgroundColor.copy(alpha = if (enabled) 1f else 0.5f)
            )
            .border(
                width = if (variant == ButtonVariant.Ghost) 1.dp else 0.dp,
                color = if (variant == ButtonVariant.Ghost) Color(0x404040) else Color.Transparent,
                shape = RoundedCornerShape(cornerRadius)
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = enabled && !isLoading
            ) { onClick() }
            .alpha(alpha)
            .padding(horizontal = paddingHorizontal, vertical = paddingVertical),
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            visible = !isLoading,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Text(
                text = text,
                fontSize = fontSize,
                fontWeight = FontWeight.SemiBold,
                color = textColor
            )
        }
        
        AnimatedVisibility(
            visible = isLoading,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            GlyphLoadingDot()
        }
    }
}

enum class ButtonVariant {
    Primary, Secondary, Ghost
}

enum class ButtonSize {
    Small, Medium, Large
}

data class Tuple4<A, B, C, D>(val a: A, val b: B, val c: C, val d: D) {
    operator fun component1() = a
    operator fun component2() = b
    operator fun component3() = c
    operator fun component4() = d
}

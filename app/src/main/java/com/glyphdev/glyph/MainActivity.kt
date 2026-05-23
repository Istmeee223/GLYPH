package com.glyphdev.glyph

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.glyphdev.glyph.ui.navigation.GlyphNavigation
import com.glyphdev.glyph.ui.theme.GlyphTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContent {
            GlyphTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0x000000)),
                    color = Color(0x000000)
                ) {
                    GlyphNavigation()
                }
            }
        }
    }
}

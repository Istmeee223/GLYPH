package com.glyphdev.glyph.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.glyphdev.glyph.data.models.Layout
import com.glyphdev.glyph.ui.screens.*

@Composable
fun GlyphNavigation() {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = "onboarding"
    ) {
        composable("onboarding") {
            OnboardingScreen(
                onComplete = {
                    navController.navigate("dashboard") {
                        popUpTo("onboarding") { inclusive = true }
                    }
                }
            )
        }
        
        composable("dashboard") {
            DashboardScreen(
                layout = null,
                onCreateLayout = {
                    navController.navigate("editor")
                },
                onEditLayout = { layout ->
                    navController.navigate("editor")
                }
            )
        }
        
        composable("editor") {
            WidgetEditorScreen(
                onBack = {
                    navController.popBackStack()
                },
                onSave = {
                    navController.navigate("preview") {
                        popUpTo("dashboard") { inclusive = false }
                    }
                }
            )
        }
        
        composable("preview") {
            HomescreenPreviewScreen(
                onEdit = {
                    navController.navigate("editor")
                }
            )
        }
        
        composable("music") {
            MusicVisualizationScreen()
        }
        
        composable("themes") {
            ThemeMarketplaceScreen(
                onSelectTheme = { theme ->
                    navController.popBackStack()
                }
            )
        }
        
        composable("settings") {
            SettingsScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}

package com.example.dynamicforms

import NavGraph
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.dynamicforms.controllers.JSONLoader
import com.example.dynamicforms.views.SectionDetailScreen
import com.example.dynamicforms.views.SectionListScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            setContent {
                NavGraph()
            }
        }
    }
}



@Composable
fun AppNavigation(navController: NavHostController, paddingValues: PaddingValues) {
    val sections = JSONLoader.loadSections(
        context = LocalContext.current
    ) // Load sections

    NavHost(
        navController,
        startDestination = "sectionList",
        modifier = Modifier.padding(paddingValues)
    ) {
        composable("sectionList") {
            SectionListScreen(navController)
        }
        composable("sectionDetail/{sectionUuid}") { backStackEntry ->
            val sectionUuid = backStackEntry.arguments?.getString("sectionUuid") ?: ""

            // ✅ Find the section with the matching UUID
            val section = sections.find { it.uuid == sectionUuid }

            // ✅ Ensure a valid section is passed
            section?.let {
                SectionDetailScreen(navController = navController, section.uuid)
            }
        }
    }
}

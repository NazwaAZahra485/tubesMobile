package com.nazwakhayla.sekaiprofileviewer.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nazwakhayla.sekaiprofileviewer.ui.page.CharacterDetailScreen
import com.nazwakhayla.sekaiprofileviewer.ui.page.CharacterIndexScreen
import com.nazwakhayla.sekaiprofileviewer.ui.page.HomeScreen
import com.nazwakhayla.sekaiprofileviewer.viewModel.AuthViewModel
import com.nazwakhayla.sekaiprofileviewer.viewModel.CharacterViewModel
import com.nazwakhayla.sekaiprofileviewer.viewModel.UnitsViewModel

@Composable
fun AppNavigation(authViewModel: AuthViewModel){
    val navController = rememberNavController()
    val categoryViewModel : CharacterViewModel = viewModel()

    NavHost(navController = navController, startDestination = Route.HOME) {
        composable(Route.HOME) {
            HomeScreen(authViewModel, navController)
        }
        composable(Route.CHARACTER_INDEX){
            CharacterIndexScreen(categoryViewModel.categories.collectAsState().value, navController)
        }
        composable(
            route = Route.CHARACTER_DETAIL,
            arguments = listOf(navArgument("characterId") { type = NavType.LongType })
        ) { backStackEntry ->

            val characterId = backStackEntry.arguments?.getLong("characterId") ?: 0L

            CharacterDetailScreen(
                characters = categoryViewModel.categories.collectAsState().value,
                navController = navController,
                selectedCharacterId = characterId
            )
        }
    }
}
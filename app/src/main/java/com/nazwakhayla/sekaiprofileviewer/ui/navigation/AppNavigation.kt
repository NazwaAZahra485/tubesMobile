package com.nazwakhayla.sekaiprofileviewer.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nazwakhayla.sekaiprofileviewer.ui.page.CategoryIndexScreen
import com.nazwakhayla.sekaiprofileviewer.ui.page.HomeScreen
import com.nazwakhayla.sekaiprofileviewer.viewModel.AuthViewModel
import com.nazwakhayla.sekaiprofileviewer.viewModel.CategoryViewModel

@Composable
fun AppNavigation(authViewModel: AuthViewModel){
    val navController = rememberNavController()
    val categoryViewModel : CategoryViewModel = viewModel()

    NavHost(navController = navController, startDestination = Route.HOME) {
        composable(Route.HOME) {
            HomeScreen(authViewModel, navController)
        }
        composable(Route.CATEGORY_INDEX){
            CategoryIndexScreen(categoryViewModel.categories.collectAsState().value, navController)
        }
    }
}
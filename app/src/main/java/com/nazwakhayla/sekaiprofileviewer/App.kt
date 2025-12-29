package com.nazwakhayla.sekaiprofileviewer

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nazwakhayla.sekaiprofileviewer.ui.navigation.AppNavigation
import com.nazwakhayla.sekaiprofileviewer.ui.page.LoginScreen
import com.nazwakhayla.sekaiprofileviewer.viewModel.AuthViewModel

@Composable
fun App(){
    val authViewModel: AuthViewModel = viewModel()
    if (authViewModel.user == null) {
        LoginScreen()
    } else{
        AppNavigation(authViewModel)
    }
}
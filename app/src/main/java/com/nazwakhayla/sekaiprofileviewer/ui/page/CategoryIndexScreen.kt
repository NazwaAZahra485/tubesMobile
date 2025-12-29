package com.nazwakhayla.sekaiprofileviewer.ui.page

import android.util.Log
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nazwakhayla.sekaiprofileviewer.entity.Category
import com.nazwakhayla.sekaiprofileviewer.ui.navigation.Route

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryIndexScreen(categories: List<Category>, navController: NavController){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Category Data (Firebase)") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Route.CATEGORY_CREATE) }
            ){
                Icon(Icons.Default.Add, "FAB Add")
            }
        }
    ) { innerPadding ->
        CategoryIndexContent(categories, Modifier.padding(innerPadding))
    }
}

@Composable
fun CategoryIndexContent(categories: List<Category>, modifier: Modifier = Modifier){
    Log.d("debug", categories.toString())
    LazyColumn(modifier = modifier.padding(8.dp)) {
        items(categories){ category ->
            Text(text = category.name)
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}
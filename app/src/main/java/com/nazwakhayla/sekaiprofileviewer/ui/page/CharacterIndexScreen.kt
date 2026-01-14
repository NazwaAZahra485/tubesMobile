package com.nazwakhayla.sekaiprofileviewer.ui.page

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.nazwakhayla.sekaiprofileviewer.entity.Character
import com.nazwakhayla.sekaiprofileviewer.ui.navigation.Route
import com.nazwakhayla.sekaiprofileviewer.viewModel.CharacterViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryIndexScreen(characters: List<Character>, navController: NavController, characterViewModel: CharacterViewModel = viewModel()){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Nama Character") }
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
        CharacterIndexContent(characters = characters, Modifier.padding(innerPadding))
    }
}

@Composable
fun CharacterIndexContent(characters: List<Character>, modifier: Modifier = Modifier){
    LazyColumn(modifier = modifier.padding(16.dp)) {
        items(characters){ character ->
            CharacterItem(character = character, modifier = Modifier)
        }
    }
}

@Composable
fun CharacterItem(character: Character, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { /* Optional: Handle item click here */ },
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = character.name,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Images available: ${character.imageUrls.size}",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
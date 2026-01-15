package com.nazwakhayla.sekaiprofileviewer.ui.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.nazwakhayla.sekaiprofileviewer.R
import com.nazwakhayla.sekaiprofileviewer.entity.Character
import com.nazwakhayla.sekaiprofileviewer.viewModel.CharacterViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(
    characters: List<Character>,
    navController: NavController,
    selectedCharacterId: Long,
    characterViewModel: CharacterViewModel = viewModel()
) {
    val selectedCharacter =
        characters.find { it.id == selectedCharacterId }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = selectedCharacter?.name ?: "Character") }
            )
        }
    ) { innerPadding ->
        CharacterDetailContent(
            characters = characters,
            selectedCharacterId = selectedCharacterId,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun CharacterDetailContent(
    characters: List<Character>,
    selectedCharacterId: Long,
    modifier: Modifier = Modifier
){
    val initialCharacter =
        characters.find { it.id == selectedCharacterId } ?: characters.first()

    var selectedCharacter by remember { mutableStateOf(initialCharacter) }

    val imageRes = getDrawableId(selectedCharacter.image_url)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // 🔹 TOP IMAGE
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = selectedCharacter.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .clip(RoundedCornerShape(20.dp))
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 🔹 BUTTON 1 & 2 (IMAGE SELECTOR)
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(characters) { character ->
                CharacterImageButton(
                    character = character,
                    isSelected = character.id == selectedCharacter.id,
                    onClick = { selectedCharacter = character }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 🔹 INFORMATION
        LazyColumn {
            item {
                CharacterInfoCard(selectedCharacter)
            }
        }
    }
}


@Composable
fun CharacterImageButton(
    character: Character,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val imageRes = getDrawableId(character.image_url)

    Card(
        modifier = Modifier.size(80.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected)
                MaterialTheme.colorScheme.primary
            else
                MaterialTheme.colorScheme.surface
        ),
        onClick = onClick
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = character.name,
            modifier = Modifier.fillMaxSize()
        )
    }
}



@Composable
fun CharacterInfoCard(character: Character) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            InfoRow("Name", character.name)
            InfoRow("Birthday", character.birthday)
            InfoRow("Gender", character.gender)
            InfoRow("School", character.school)
            InfoRow("Voice Actor", character.voice_actor)
        }
    }
}


@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, style = MaterialTheme.typography.labelMedium)
        Text(text = value, style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun getDrawableId(name: String): Int {
    val context = androidx.compose.ui.platform.LocalContext.current
    return remember(name) {
        context.resources.getIdentifier(
            name,
            "drawable",
            context.packageName
        )
    }
}
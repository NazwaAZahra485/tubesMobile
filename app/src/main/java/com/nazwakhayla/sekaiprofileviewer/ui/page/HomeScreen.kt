package com.nazwakhayla.sekaiprofileviewer.ui.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.nazwakhayla.sekaiprofileviewer.entity.SekaiUnit
import com.nazwakhayla.sekaiprofileviewer.ui.navigation.Route
import com.nazwakhayla.sekaiprofileviewer.viewModel.AuthViewModel
import com.nazwakhayla.sekaiprofileviewer.viewModel.UnitsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    authViewModel: AuthViewModel,
    navController: NavController,
    unitsViewModel: UnitsViewModel = viewModel()
) {
    val units by unitsViewModel.units.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Project Sekai Units") },
                actions = {
                    IconButton(onClick = { authViewModel.signOut() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Logout,
                            contentDescription = "Sign Out"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        if (units.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    CircularProgressIndicator()
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Fetching Units...", style = MaterialTheme.typography.bodySmall)
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(units) { unit ->
                    UnitItem(
                        unit = unit,
                        onMemberClick = { memberId ->
                            // Navigate to character screen, optionally passing ID
                            navController.navigate(Route.CATEGORY_INDEX)
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun UnitItem(
    unit: SekaiUnit,
    onMemberClick: (Long) -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            UnitLogoImage(logo_url = unit.logo_url, contentDescription = unit.unit_name)

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = unit.unit_name,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = unit.japanese_name,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.secondary
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = unit.description,
                    style = MaterialTheme.typography.bodyMedium,
                )

                Spacer(modifier = Modifier.height(12.dp))

                // --- MEMBERS SECTION ---
                Text("Members", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary)
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    items(unit.members) { memberId ->
                        MemberChip(id = memberId, onClick = { onMemberClick(memberId) })
                    }
                }

                // --- VIRTUAL SINGERS SECTION ---
                Text("Main Virtual Singers", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary)
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    items(unit.main_vs) { vsId ->
                        MemberChip(id = vsId, onClick = { onMemberClick(vsId) })
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Sekai: ${unit.sekai}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.outline
                )
            }
        }
    }
}

@Composable
fun MemberChip(id: Long, onClick: () -> Unit) {
    Surface(
        onClick = onClick,
        shape = MaterialTheme.shapes.small,
        color = MaterialTheme.colorScheme.secondaryContainer,
        tonalElevation = 2.dp
    ) {
        Text(
            text = "ID: $id", // In the future, you'd fetch the name here
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun UnitLogoImage(logo_url: String, contentDescription: String) {
    val context = LocalContext.current

    val imageResId = remember(logo_url) {
        context.resources.getIdentifier(
            logo_url,
            "drawable",
            context.packageName
        )
    }

    if (imageResId != 0) {
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = contentDescription,
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .padding(10.dp),
            contentScale = ContentScale.Fit
        )
    }
}
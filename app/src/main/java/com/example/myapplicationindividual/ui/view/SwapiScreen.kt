package com.example.myapplicationindividual.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplicationindividual.domain.model.StarWarsCharacter
import com.example.myapplicationindividual.ui.viewmodel.SwapiViewModel

@Composable
fun SwapiScreen(
    viewModel: SwapiViewModel = remember { SwapiViewModel() }
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Star Wars API (SWAPI)",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Personaje ID: ${uiState.currentId}",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.secondary
                )

                if (uiState.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.padding(24.dp))
                } else {
                    val character: StarWarsCharacter? = uiState.character
                    if (character != null) {
                        Text(
                            text = character.name,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )

                        Column(
                            verticalArrangement = Arrangement.spacedBy(6.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            DetailItem(label = "Altura", value = "${character.height} cm")
                            DetailItem(label = "Peso", value = "${character.mass} kg")
                            DetailItem(label = "Género", value = character.gender)
                            DetailItem(label = "Año de Nacimiento", value = character.birthYear)
                        }
                    }
                }

                uiState.errorMessage?.let { error ->
                    Text(
                        text = error,
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.Center,
                        fontSize = 14.sp
                    )
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedButton(
                onClick = { viewModel.loadPreviousCharacter() },
                enabled = !uiState.isLoading && uiState.currentId > 1,
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp)
            ) {
                Text(text = "Anterior")
            }

            Button(
                onClick = { viewModel.loadNextCharacter() },
                enabled = !uiState.isLoading,
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp)
            ) {
                Text(text = "Siguiente")
            }
        }
    }
}

@Composable
private fun DetailItem(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "$label:", fontWeight = FontWeight.SemiBold)
        Text(text = value)
    }
}

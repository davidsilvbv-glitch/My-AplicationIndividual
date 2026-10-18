package com.example.myapplicationindividual.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.myapplicationindividual.domain.model.MarvelCharacter
import com.example.myapplicationindividual.ui.viewmodel.MarvelViewModel

@Composable
fun MarvelScreen(
    viewModel: MarvelViewModel = remember { MarvelViewModel() }
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Marvel Characters API",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(12.dp))

        uiState.errorMessage?.let { msg ->
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            ) {
                Text(
                    text = msg,
                    color = MaterialTheme.colorScheme.onErrorContainer,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center
                )
            }
        }

        if (uiState.isLoading) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                items(uiState.characters) { character ->
                    MarvelCharacterItem(character = character)
                }
            }
        }
    }
}

@Composable
fun MarvelCharacterItem(character: MarvelCharacter) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (character.imageUrl.isNotEmpty()) {
                AsyncImage(
                    model = character.imageUrl,
                    contentDescription = character.name,
                    modifier = Modifier
                        .size(80.dp)
                        .padding(end = 12.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = character.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = character.description,
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 3
                )
            }
        }
    }
}

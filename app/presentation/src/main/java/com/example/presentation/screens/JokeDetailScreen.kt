package com.example.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.presentation.R
import com.example.presentation.genericViews.topAppBars.CustomTopAppBar
import com.example.presentation.viewModels.JokeViewModel

@Composable
fun JokeDetailScreen(
    category: String,
    displayName: String,
    viewModel: JokeViewModel,
    onBackClick: () -> Unit
) {
    val jokeState = viewModel.joke.collectAsState()
    val errorState = viewModel.error.collectAsState()
    var isEnabled by remember { mutableStateOf(true) }

    LaunchedEffect(category) {
        viewModel.loadJoke(category)
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = displayName,
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = dimensionResource(R.dimen.padding_horizontal)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Divider(
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.9f),
                thickness = dimensionResource(R.dimen.thickness_3dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = dimensionResource(R.dimen.padding_bottom_16dp))
            )

            errorState.value?.let { error ->
                Text(
                    text = error,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_bottom_16dp))
                )
                Button(
                    onClick = {
                            viewModel.clearError()
                            viewModel.loadJoke(category)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(stringResource(R.string.retry))
                }
            } ?: jokeState.value?.let { joke ->
                Text(
                    text = joke.text,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_bottom_16dp))
                )
                Button(
                    onClick = {
                        if (isEnabled) {
                            isEnabled = false
                            viewModel.addToFavorites(joke)
                        }
                    },
                    enabled = isEnabled,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(stringResource(R.string.add_to_favorites))
                }
            }
        }
    }
}
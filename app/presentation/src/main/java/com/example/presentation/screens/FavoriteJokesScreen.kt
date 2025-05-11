package com.example.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.presentation.R
import com.example.presentation.listItems.FavoriteJokeItem
import com.example.presentation.genericViews.topAppBars.CustomTopAppBar
import com.example.presentation.viewModels.JokeViewModel

@Composable
fun FavoriteJokesScreen(
    viewModel: JokeViewModel,
    onBackClick: () -> Unit
) {
    val favoriteJokes = viewModel.favoriteJokes.collectAsState()

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = stringResource(R.string.favorite_jokes),
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = dimensionResource(R.dimen.padding_horizontal))
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.vertical_arrangement)),
                modifier = Modifier.fillMaxSize()
            ) {
                if (favoriteJokes.value.isEmpty()) {
                    item {
                        Text(
                            text = stringResource(R.string.no_favorite_jokes),
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = dimensionResource(R.dimen.padding_vertical_16dp))
                                .align(Alignment.CenterHorizontally)
                        )
                    }
                } else {
                    items(favoriteJokes.value.size) { index ->
                        FavoriteJokeItem(
                            joke = favoriteJokes.value[index],
                            onRemove = { viewModel.removeFavoriteJoke(favoriteJokes.value[index].id) }
                        )
                    }
                }
            }
        }
    }
}
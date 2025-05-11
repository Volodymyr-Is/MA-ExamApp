package com.example.presentation.listItems

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.domain.model.Joke
import com.example.presentation.R

@Composable
fun FavoriteJokeItem(joke: Joke, onRemove: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = dimensionResource(R.dimen.padding_vertical_8dp))
    ) {
        Divider(
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.9f),
            thickness = dimensionResource(R.dimen.thickness_3dp),
            modifier = Modifier
                .fillMaxWidth()
        )
        Text(
            text = stringResource(R.string.joke_category) + joke.category,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_bottom_8dp), top = dimensionResource(R.dimen.padding_top_8dp))
        )
        Divider(
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
            thickness = dimensionResource(R.dimen.thickness_1dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(R.dimen.padding_horizontal))
        )
        Text(
            text = joke.text,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(vertical = dimensionResource(R.dimen.padding_vertical_8dp))
        )
        Button(
            onClick = onRemove,
            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_top_8dp), bottom = dimensionResource(R.dimen.padding_bottom_4dp))
        ) {
            Text(stringResource(R.string.remove_from_favorites))
        }
        Divider(
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.9f),
            thickness = dimensionResource(R.dimen.thickness_3dp),
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}
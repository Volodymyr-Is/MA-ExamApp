package com.example.presentation.listItems

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.presentation.R

@Composable
fun CategoryItem(
    category: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = dimensionResource(R.dimen.padding_vertical_16dp), horizontal = dimensionResource(R.dimen.padding_horizontal))
    ) {
        Text(
            text = category,
            style = MaterialTheme.typography.titleMedium
        )
    }
    Divider(
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.9f),
        thickness = dimensionResource(R.dimen.thickness_1dp),
        modifier = Modifier
            .fillMaxWidth()
    )
}
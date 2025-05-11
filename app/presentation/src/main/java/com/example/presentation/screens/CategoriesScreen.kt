package com.example.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.presentation.R
import com.example.presentation.listItems.CategoryItem

@Composable
fun CategoriesScreen(
    onCategoryClick: (String, String) -> Unit,
    onFavoritesClick: () -> Unit
) {
    val categoryMap = listOf(
        "Family Jokes" to "family",
        "Blonde Jokes" to "blonde",
        "Animal Jokes" to "animal",
        "Clean Jokes" to "clean",
        "Yo_Mama Jokes" to "yo_mama",
        "Relationship Jokes" to "relationship",
        "Office Jokes" to "office",
        "Insult Jokes" to "insult",
        "Holiday Jokes" to "holiday",
        "Food Jokes" to "food",
        "Dark Jokes" to "dark",
        "Jokes" to "common",
        "Doctor Jokes" to "doctor",
        "Engineer Jokes" to "engineer"
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = dimensionResource(R.dimen.padding_horizontal))
    ) {
        item {
            CategoryItem(
                category = stringResource(R.string.favorite_jokes),
                onClick = { onFavoritesClick() }
            )
        }
        items(categoryMap.size) { index ->
            val (displayName, endpoint) = categoryMap[index]
            CategoryItem(
                category = displayName,
                onClick = { onCategoryClick(displayName, endpoint) }
            )
        }
    }
}
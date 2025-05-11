package com.example.presentation.koinModules

import com.example.presentation.viewModels.JokeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { JokeViewModel(get(), get(), get(), get()) }
}
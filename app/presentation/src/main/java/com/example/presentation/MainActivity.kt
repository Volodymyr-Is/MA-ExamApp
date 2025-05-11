package com.example.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.exam.presentation.JokesApp
import com.example.presentation.ui.theme.Hw4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Hw4Theme {
                JokesApp(activity = this)
            }
        }
    }
}
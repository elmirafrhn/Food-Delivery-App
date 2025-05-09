package com.example.woltassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.ui.unit.dp
import com.example.venues.presentation.theme.AppTheme
import com.example.venues.presentation.ui.VenuesScreenContainer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface(tonalElevation = 2.dp) {
                    VenuesScreenContainer()
                }
            }
        }
    }
}
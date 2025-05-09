package com.example.venues.presentation.ui


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerLoadingScreen(modifier: Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        items(5) {
            ShimmerItemComposable()
        }
    }
}

@Composable
fun ShimmerItemComposable() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        ShimmerContainer(
            modifier = Modifier
                .height(192.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
        )

        Spacer(modifier = Modifier.height(8.dp))

        ShimmerContainer(
            modifier = Modifier
                .padding(start = 8.dp)
                .height(20.dp)
                .fillMaxWidth(0.6f)
                .clip(RoundedCornerShape(4.dp))
        )

        Spacer(modifier = Modifier.height(8.dp))

        ShimmerContainer(
            modifier = Modifier
                .padding(start = 8.dp)
                .height(16.dp)
                .fillMaxWidth(0.4f)
                .clip(RoundedCornerShape(4.dp))
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ShimmerLoadingScreenPreview() {
    MaterialTheme {
        ShimmerLoadingScreen(modifier = Modifier)
    }
}
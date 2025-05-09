package com.example.venues.presentation.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.venues.R
import com.example.venues.domain.model.FavoriteModel
import com.example.venues.domain.model.VenueModel

@Composable
fun ItemComposable(
    modifier: Modifier,
    venue: VenueModel,
    onFavoriteClicked: (FavoriteModel) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        ImageWithFavoriteComposable(
            isFavorite = venue.isFavorite,
            name = venue.name,
            imageUrl = venue.imageUrl
        ) {
            onFavoriteClicked(FavoriteModel(venue.id, !venue.isFavorite))
        }
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = venue.name,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = venue.description.orEmpty(),
            style = MaterialTheme.typography.titleSmall,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
internal fun ImageWithFavoriteComposable(
    isFavorite: Boolean,
    name: String,
    imageUrl: String,
    onClick: () -> Unit
) {
    val alpha by animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(durationMillis = 300)
    )

    Box {
        Image(
            painter = rememberAsyncImagePainter(model = imageUrl),
            contentDescription = name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(192.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
        )
        Box(
            modifier = Modifier
                .padding(8.dp)
                .size(34.dp)
                .background(
                    color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f),
                    shape = CircleShape
                )
                .align(Alignment.BottomEnd)

        ) {
            IconButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd),
                onClick = onClick
            ) {
                Icon(
                    painter = if (isFavorite) painterResource(R.drawable.favorite_filled) else painterResource(
                        R.drawable.favorite
                    ),
                    contentDescription = "Favorite",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .alpha(alpha)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemComposablePreview() {
    val sampleVenue = VenueModel(
        id = "1",
        name = "Sample Venue",
        description = "Sample description for the venue",
        imageUrl = "https://example.com/sample.jpg",
        isFavorite = false
    )

    ItemComposable(
        modifier = Modifier,
        venue = sampleVenue,
        onFavoriteClicked = { favoriteItem ->
        }
    )
}
package com.example.venues.presentation.ui


import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerContainer(
    modifier: Modifier = Modifier,
    animationDuration: Int = 1200,
    animationDelay: Int = 0,
    gradientThickness: Dp = 128.dp,
    colorGradientList: List<Color> = listOf(
        MaterialTheme.colorScheme.surfaceContainerHighest.copy(alpha = 0.2f),
        MaterialTheme.colorScheme.surfaceContainerHighest.copy(alpha = 0.9f),
        MaterialTheme.colorScheme.surfaceContainerHighest.copy(alpha = 0.2f),
    ),
) {
    val infTrans = rememberInfiniteTransition(label = "")
    val animationSpec = infiniteRepeatable<Float>(
        animation = tween(
            durationMillis = animationDuration,
            easing = LinearEasing,
            delayMillis = animationDelay,
        ),
        repeatMode = RepeatMode.Restart,
    )
    val xShimmer = infTrans.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = animationSpec,
        label = "",
    )
    val yShimmer = infTrans.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = animationSpec,
        label = "",
    )
    val gradientWidthPx = with(LocalDensity.current) { gradientThickness.toPx() }

    Box(
        modifier = modifier.drawWithContent {
            drawContent()
            drawAnimatedShimmerFullSize(
                xPos = xShimmer.value,
                yPos = yShimmer.value,
                gradientWidth = gradientWidthPx,
                gradientColors = colorGradientList,
            )
        },
    )
}

fun DrawScope.drawAnimatedShimmerFullSize(
    xPos: Float,
    yPos: Float,
    gradientWidth: Float,
    gradientColors: List<Color>
) {
    val absoluteXPos = (((size.width + gradientWidth) / 100) * (xPos * 100))
    val absoluteYPos = (((size.height + gradientWidth) / 100) * (yPos * 100))
    val gradientBrush = Brush.linearGradient(
        colors = gradientColors,
        start = Offset(
            absoluteXPos - gradientWidth,
            absoluteYPos - gradientWidth,
        ),
        end = Offset(absoluteXPos, absoluteYPos),
    )
    drawRect(
        brush = gradientBrush,
        topLeft = Offset(0f, 0f),
        size = size,
    )
}

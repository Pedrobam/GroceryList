package br.com.bamtech.grocerylist.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class Spacing(
    val extraSmall: Dp,
    val small: Dp,
    val medium: Dp,
    val large: Dp,
    val extraLarge: Dp,
)

private val DefaultSpacing = Spacing(
    extraSmall = 4.dp,
    small = 8.dp,
    medium = 16.dp,
    large = 24.dp,
    extraLarge = 32.dp,
)

private val LocalSpacing = staticCompositionLocalOf {
    DefaultSpacing
}

val MaterialTheme.spacing: Spacing
    @Composable
    get() = LocalSpacing.current


@Composable
internal fun ProvideSpacing(
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalSpacing provides DefaultSpacing,
        content = content,
    )
}

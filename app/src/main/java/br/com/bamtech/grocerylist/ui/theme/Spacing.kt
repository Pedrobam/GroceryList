package br.com.bamtech.grocerylist.ui.theme

import androidx.compose.runtime.Immutable
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

val LocalSpacing = Spacing(
    extraSmall = 4.dp,
    small = 8.dp,
    medium = 16.dp,
    large = 32.dp,
    extraLarge = 64.dp,
)
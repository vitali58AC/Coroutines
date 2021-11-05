package com.skillbox.github.ui.compose

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.skillbox.github.ui.current_user.CurrentUserViewModel

@Composable
fun ProgressIndicator(visibility: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (visibility) {
            CircularProgressIndicator()
        }
    }
}
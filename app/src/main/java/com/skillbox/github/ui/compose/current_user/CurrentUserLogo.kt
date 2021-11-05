package com.skillbox.github.ui.compose.current_user

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.skillbox.github.R
import com.skillbox.github.ui.current_user.CurrentUserViewModel

@Composable
fun CurrentUserLogo(url: String, size: Dp) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 50.dp, 16.dp, 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberImagePainter(
                data = url,
                onExecute = { _, _ -> true },
                builder = {
                    crossfade(true)
                    placeholder(R.drawable.placeholder)
                    transformations(CircleCropTransformation())
                }
            ),
            contentDescription = stringResource(R.string.user_logo),
            modifier = Modifier.size(size)
        )
    }
}
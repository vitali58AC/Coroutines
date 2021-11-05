package com.skillbox.github.ui.compose.repository_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skillbox.github.R
import com.skillbox.github.ui.compose.TextInfo
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun RepositoryItem(
    id: String,
    name: String,
    owner: String,
    avatar: String,
    url: String,
    ownerTag: String
) {
    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(2f)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Bottom
            ) {
                TextInfo(
                    tag = stringResource(id = R.string.id),
                    text = id,
                    fontSize = 14.sp,
                    padding = 2.dp
                )
                TextInfo(
                    tag = stringResource(id = R.string.name),
                    text = name,
                    fontSize = 14.sp,
                    padding = 2.dp
                )
            }
            Column(
                modifier = Modifier
                    .weight(2f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                TextInfo(
                    tag = ownerTag,
                    text = owner,
                    fontSize = 14.sp
                )
            }
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .weight(2f)
                    .fillMaxSize()
            ) {
                GlideImage(
                    imageModel = avatar,
                    contentScale = ContentScale.Crop,
                    circularReveal = CircularReveal(duration = 250),
                    placeHolder = ImageBitmap.imageResource(R.drawable.placeholder),
                    modifier = Modifier.clip(CircleShape).size(64.dp),
                    contentDescription = stringResource(R.string.user_logo)
                )
            }

        }
        TextInfo(
            tag = stringResource(id = R.string.url),
            text = url,
            fontSize = 14.sp,
            padding = 2.dp
        )
    }
}
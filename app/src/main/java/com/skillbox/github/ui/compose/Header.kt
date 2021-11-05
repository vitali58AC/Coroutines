package com.skillbox.github.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun Header(text: String) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = text,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }

}
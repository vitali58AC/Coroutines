package com.skillbox.github.ui.compose.repository_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skillbox.github.R
import com.skillbox.github.ui.repository_list.RepositoriesViewModel

@Composable
fun Starred(repositoryViewModel: RepositoriesViewModel, owner: String, repo: String) {
    val starred = repositoryViewModel.starred.value
    val color = if (starred) Color.Red else Color.LightGray
    val text = if (starred) "Unstar" else "Put a star"

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(modifier = Modifier.clickable {
            if (starred) {
            repositoryViewModel.deleteStarred(owner = owner, repo = repo)
            } else {
                repositoryViewModel.putStarred(owner = owner, repo = repo)
            }
        }) {
            Row(
                modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.star),
                    contentDescription = "Starred status",
                    Modifier.size(40.dp),
                    colorFilter = ColorFilter.tint(color)
                )
                Text(text = text, fontSize = 20.sp)
            }
        }
    }
}

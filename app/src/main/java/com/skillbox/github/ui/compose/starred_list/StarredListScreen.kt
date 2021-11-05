package com.skillbox.github.ui.compose.starred_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.skillbox.github.R
import com.skillbox.github.ui.compose.Header
import com.skillbox.github.ui.compose.ProgressIndicator
import com.skillbox.github.ui.compose.repository_list.RepositoryItem
import com.skillbox.github.ui.starred_list.StarredListViewModel

@Composable
fun StarredListScreen(
    repositoriesViewModel: StarredListViewModel
) {
    val repos = repositoriesViewModel.starredRepositoriesList
    ProgressIndicator(visibility = repositoriesViewModel.isStarredLoading.value)
    Column {
        Header(text = stringResource(R.string.list_starred_repositories))
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(
                items = repos,
                itemContent = {
                    Card(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        RepositoryItem(
                            id = it.id.toString(),
                            name = it.name.toString(),
                            owner = it.owner.login,
                            avatar = it.owner.avatarUrl,
                            url = it.htmlUrl,
                            ownerTag = stringResource(R.string.owner)
                        )
                    }
                }
            )
        }
    }
}
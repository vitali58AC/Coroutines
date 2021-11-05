package com.skillbox.github.ui.compose.repository_list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.skillbox.github.R
import com.skillbox.github.ui.compose.TextInfo
import com.skillbox.github.ui.compose.current_user.CurrentUserLogo
import com.skillbox.github.ui.repository_list.RepositoriesViewModel

@Composable
fun DetailRepositoryBody(viewModel: RepositoriesViewModel) {
    val owner = viewModel.currentRepository[0].owner.login
    val repo = viewModel.currentRepository[0].name.toString()
    val userInfo = mapOf(
        stringResource(R.string.id) to viewModel.currentRepository[0].id.toString(),
        stringResource(R.string.name) to viewModel.currentRepository[0].name.toString(),
        stringResource(R.string.full_name) to viewModel.currentRepository[0].fullName.toString(),
        stringResource(R.string.url) to viewModel.currentRepository[0].htmlUrl,
        stringResource(R.string.description) to viewModel.currentRepository[0].description.toString(),
        stringResource(R.string.type) to viewModel.currentRepository[0].owner.type
    )

    CheckStarred(owner = owner, repo = repo, checkStarred = viewModel::checkStarred)
    LazyColumn {
        item {
            CurrentUserLogo(
                url = viewModel.currentRepository[0].owner.avatarUrl,
                size = 206.dp
            )
        }
        item {
            Starred(viewModel, owner, repo)
        }
        for ((tag, text) in userInfo) {
            item {
                TextInfo(tag = tag, text = text)
            }
        }
    }
}

@Composable
fun CheckStarred(owner: String, repo: String, checkStarred: (String, String) -> Unit) {
    val currentCall by rememberUpdatedState(checkStarred)

    LaunchedEffect(true) {
        currentCall(owner, repo)
    }
}
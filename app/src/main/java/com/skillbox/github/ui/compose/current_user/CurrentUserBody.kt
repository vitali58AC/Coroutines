package com.skillbox.github.ui.compose.current_user

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.skillbox.github.R
import com.skillbox.github.ui.compose.Header
import com.skillbox.github.ui.compose.TextInfo
import com.skillbox.github.ui.compose.repository_list.RepositoryItem
import com.skillbox.github.ui.current_user.CurrentUserViewModel

@Composable
fun CurrentUserBody(viewModel: CurrentUserViewModel) {
    val userInfo = mapOf(
        stringResource(R.string.id) to viewModel.objectState[0].id.toString(),
        stringResource(R.string.name) to viewModel.objectState[0].name.toString(),
        stringResource(R.string.url) to viewModel.objectState[0].url,
        stringResource(R.string.type) to viewModel.objectState[0].type,
        stringResource(R.string.company) to viewModel.objectState[0].company.toString(),
        stringResource(R.string.location) to viewModel.objectState[0].location.toString(),
        stringResource(R.string.public_repos) to viewModel.objectState[0].publicRepos.toString(),
        stringResource(R.string.followers) to viewModel.objectState[0].followers.toString(),
        stringResource(R.string.following) to viewModel.objectState[0].following.toString(),
        stringResource(R.string.created_at) to viewModel.objectState[0].createdAt.toString(),
        stringResource(R.string.updated_at) to viewModel.objectState[0].updatedAt.toString()
    )
    val followingList = viewModel.followingList.toList()
    LazyColumn {
        item {
            CurrentUserLogo(viewModel.objectState[0].avatarUrl, 206.dp)
        }
        item {
            EditName(viewModel)
        }
        for ((tag, text) in userInfo) {
            item {
                TextInfo(tag = tag, text = text)
            }
        }
        item {
            Header(text = "User Following")
        }
        for (item in followingList) {
            item {
                Card(
                    modifier = Modifier.padding(8.dp)
                ) {
                    RepositoryItem(
                        id = item.id.toString(),
                        name = item.name.toString(),
                        owner = item.login,
                        avatar = item.avatarUrl,
                        url = item.url,
                        ownerTag = stringResource(id = R.string.login)
                    )
                }
            }
        }
    }
}

@Composable
fun EditName(viewModel: CurrentUserViewModel) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        InputNameDialog(viewModel = viewModel)
    }
}


package com.skillbox.github.ui.compose.repository_list

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.skillbox.github.ui.compose.ErrorMessage
import com.skillbox.github.ui.compose.Header
import com.skillbox.github.ui.repository_list.RepositoriesViewModel


@Composable
fun DetailRepositoryScreen(repositoryViewModel: RepositoriesViewModel) {
    val ownerName = repositoryViewModel.currentRepository[0].owner.login
    val isError = repositoryViewModel.isError.value
    val errorMessage = repositoryViewModel.errorMessage.value
    Column {
        Header(text = "Owner: $ownerName")
        DetailRepositoryBody(viewModel = repositoryViewModel)
    }
    if (isError) {
        ErrorMessage(
            visibility = isError,
            message = errorMessage
        )
    }
}




package com.skillbox.github.ui.compose.current_user

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.skillbox.github.ui.compose.ErrorMessage
import com.skillbox.github.ui.compose.Header
import com.skillbox.github.ui.compose.ProgressIndicator
import com.skillbox.github.ui.current_user.CurrentUserViewModel

@Composable
fun CurrentUserScreen(currentUserViewModel: CurrentUserViewModel) {
    ProgressIndicator(currentUserViewModel.isLoading.value)
    if (!currentUserViewModel.isLoading.value && !currentUserViewModel.isError.value) {
        Column {
            Header(text = "Profile: ${currentUserViewModel.objectState[0].login}")
            CurrentUserBody(viewModel = currentUserViewModel)
        }
    }
    if (currentUserViewModel.isError.value) {
        ErrorMessage(
            visibility = currentUserViewModel.isError.value,
            message = currentUserViewModel.errorMessage.value
        )
    }
}




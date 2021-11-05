package com.skillbox.github.ui.current_user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewmodel.compose.viewModel
import com.skillbox.github.ui.compose.current_user.CurrentUserScreen

class CurrentUserFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val currentUserViewModel: CurrentUserViewModel = viewModel()
                currentUserViewModel.currentUser()
                CurrentUserScreen(currentUserViewModel = currentUserViewModel)
            }
        }
    }
}


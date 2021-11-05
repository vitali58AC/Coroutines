package com.skillbox.github.ui.starred_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewmodel.compose.viewModel
import com.skillbox.github.ui.compose.starred_list.StarredListScreen

class StarredListFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val starredListViewModel: StarredListViewModel = viewModel()
                starredListViewModel.getStarredRepositories()
                StarredListScreen(repositoriesViewModel = starredListViewModel)
            }
        }
    }
}
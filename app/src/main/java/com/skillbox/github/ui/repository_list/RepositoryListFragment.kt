package com.skillbox.github.ui.repository_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.skillbox.github.ui.compose.repository_list.DetailRepositoryScreen
import com.skillbox.github.ui.compose.repository_list.RepositoriesScreen

class RepositoryListFragment : Fragment() {

    private val repositoryViewModel: RepositoriesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val navController = rememberNavController()
                GetRepositories(repositoryViewModel::getRepositories)
                NavigationComponent(navController = navController, viewModel = repositoryViewModel)
            }
        }
    }
}

@Composable
fun NavigationComponent(navController: NavHostController, viewModel: RepositoriesViewModel) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            RepositoriesScreen(navController, viewModel)
        }
        composable("details") {
            DetailRepositoryScreen(viewModel)
        }
    }
}

@Composable
fun GetRepositories(getRepositories: () -> Unit) {
    val currentCall by rememberUpdatedState(getRepositories)

    LaunchedEffect(true) {
        currentCall()
    }
}
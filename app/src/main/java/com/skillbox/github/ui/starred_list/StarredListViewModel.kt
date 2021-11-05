package com.skillbox.github.ui.starred_list

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skillbox.github.network.Repositories
import kotlinx.coroutines.launch

class StarredListViewModel: ViewModel() {

    var starredRepositoriesList = mutableStateListOf<Repositories>()
    var isStarredLoading = mutableStateOf(true)


    private val repository = StarredListRepository()

    private fun updateRepositories(
        oldRepositories: SnapshotStateList<Repositories>,
        newRepositories: List<Repositories>
    ) {
        oldRepositories.clear()
        oldRepositories.addAll(newRepositories)
    }

    fun getStarredRepositories() {
        viewModelScope.launch {
            try {
                val repositories = repository.getStarredRepositories()
                updateRepositories(starredRepositoriesList, repositories)
            } catch (t: Throwable) {
                starredRepositoriesList.clear()
            } finally {
                isStarredLoading.value = false
            }
        }
    }
}
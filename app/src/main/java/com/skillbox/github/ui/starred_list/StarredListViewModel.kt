package com.skillbox.github.ui.starred_list

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.skillbox.github.network.Repositories

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
        repository.getStarredRepositories(
            onComplete = { repositories ->
                isStarredLoading.value = false
                Log.e("tags", "onComplete starred repo $repositories")
                updateRepositories(starredRepositoriesList, repositories)
            },
            onError = {
                isStarredLoading.value = false
                starredRepositoriesList.clear()
            }
        )
    }
}
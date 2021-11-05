package com.skillbox.github.ui.repository_list

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skillbox.github.network.Repositories
import kotlinx.coroutines.*

class RepositoriesViewModel : ViewModel() {

    private val repository = RepositoriesListRepository()
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    var objectState = mutableStateListOf<Repositories>()
    var isLoading = mutableStateOf(true)
    var currentRepository = mutableStateListOf<Repositories>()
    var isError = mutableStateOf(false)
    var errorMessage = mutableStateOf("")
    var starred = mutableStateOf(false)

    private fun updateRepositories(
        oldRepositories: SnapshotStateList<Repositories>,
        newRepositories: List<Repositories>
    ) {
        oldRepositories.clear()
        oldRepositories.addAll(newRepositories)
    }

    fun setCurrentRepository(repository: Repositories) {
        currentRepository.clear()
        currentRepository.add(repository)
    }

    fun getRepositories() {
        scope.launch {
            try {
                val repositories = repository.getRepositories()
                updateRepositories(objectState, repositories)
            } catch (t: Throwable) {
                isError.value = true
                errorMessage.value = "Error: $t"
                objectState.clear()
            } finally {
                isLoading.value = false
            }
        }
    }

    fun checkStarred(owner: String, repo: String) {
        viewModelScope.launch {
            yield()
            try {
                starred.value = repository.checkStarred(owner, repo)
            } catch (t: Throwable) {
                Log.e("star", "onError check star $t")
            }
        }
    }

    fun putStarred(owner: String, repo: String) {
        viewModelScope.launch {
            yield()
            try {
                if (repository.putStarred(owner, repo)) {
                    starred.value = true
                }
            } catch (t: Throwable) {
                Log.e("star", "onError put star $t")
            }
        }
    }

    fun deleteStarred(owner: String, repo: String) {
        viewModelScope.launch {
            yield()
            try {
                if (repository.deleteStarred(owner, repo)) {
                    starred.value = false
                }
            } catch (t: Throwable) {
                Log.e("star", "onError delete star $t")
            }
        }
    }

    override fun onCleared() {
        scope.cancel()
        super.onCleared()
    }
}
package com.skillbox.github.ui.current_user

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skillbox.github.network.User
import kotlinx.coroutines.*

class CurrentUserViewModel : ViewModel() {

    private val repository = CurrentUserRepository()
    private val errorHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        isLoading.value = false
        isError.value = true
        errorMessage.value = "Error: $throwable with context: $coroutineContext"
        Log.e("tags", "error from coroutine current user fragment $throwable")
        objectState.clear()
    }
    private val scope = CoroutineScope(errorHandler + Dispatchers.Main)

    var objectState = mutableStateListOf<User>()
    var followingList = mutableStateListOf<User>()
    var isLoading = mutableStateOf(true)
    var isError = mutableStateOf(false)
    var errorMessage = mutableStateOf("")


    private fun updateUser(newUser: User) {
        objectState.clear()
        objectState.add(newUser)
    }

    private fun updateFollowing(newUsers: List<User>) {
        followingList.clear()
        followingList.addAll(newUsers)
    }

    fun currentUser() {
        scope.launch {
            val currentUser = async {
                repository.currentUser()
            }
            val userFollowing = async {
                repository.userFollowing()
            }
            updateUser(currentUser.await())
            updateFollowing(userFollowing.await())
            isLoading.value = false
            Log.e("tags", "onComplete size following ${followingList.size}")
        }
    }

    fun patchUserName(name: String) {
        viewModelScope.launch {
            yield()
            try {
                val user = repository.patchUserName(name)
                updateUser(user)
            } catch (t: Throwable) {
                Log.e("tags", "onError in patch name $t")
            }
        }
    }
}
package com.skillbox.github.ui.repository_list

import com.skillbox.github.data.AuthRepository
import com.skillbox.github.data.Constants
import com.skillbox.github.network.Network
import com.skillbox.github.network.Repositories
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class RepositoriesListRepository {


    @Suppress("BlockingMethodInNonBlockingContext")
    suspend fun getRepositories(): List<Repositories> {
        return withContext(Dispatchers.IO) {
            Network.gitHubApi.getRepositories("token " + AuthRepository.accessToken).execute()
                .body().orEmpty()
        }
    }


    suspend fun checkStarred(
        owner: String,
        repo: String
    ): Boolean {
        return suspendCancellableCoroutine {
            Network.gitHubApi.checkStarred("token " + AuthRepository.accessToken, owner, repo)
                .enqueue(
                    object : Callback<String> {

                        override fun onResponse(
                            call: Call<String>,
                            response: Response<String>
                        ) {
                            it.resume(response.code().toString() == Constants.STARRED_OK)
                        }

                        override fun onFailure(call: Call<String>, t: Throwable) {
                            it.resumeWithException(t)
                        }
                    }
                )
        }
    }

    suspend fun putStarred(
        owner: String,
        repo: String
    ): Boolean {
        val response =
            Network.gitHubApi.putStarred("token " + AuthRepository.accessToken, owner, repo)
                .code()
                .toString()
        return response == Constants.STARRED_OK
    }

    suspend fun deleteStarred(
        owner: String,
        repo: String
    ): Boolean {
        val response =
            Network.gitHubApi.deleteStarred("token " + AuthRepository.accessToken, owner, repo)
                .code()
                .toString()
        return response == Constants.STARRED_OK
    }
}
package com.skillbox.github.ui.starred_list

import android.util.Log
import com.skillbox.github.data.AuthRepository
import com.skillbox.github.network.Network
import com.skillbox.github.network.Repositories
import com.skillbox.github.network.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StarredListRepository {

    fun getStarredRepositories(
        onComplete: (List<Repositories>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        Network.gitHubApi.getStarred("token " + AuthRepository.accessToken).enqueue(
            object : Callback<List<Repositories>> {

                override fun onResponse(
                    call: Call<List<Repositories>>,
                    response: Response<List<Repositories>>
                ) {
                    if (response.isSuccessful) {
                        val repositories = response.body()?.onEach {
                            Repositories(
                                id = it.id,
                                name = it.name,
                                fullName = it.fullName,
                                owner = User(
                                    login = it.owner.login,
                                    id = it.owner.id,
                                    avatarUrl = it.owner.avatarUrl,
                                    name = it.owner.name,
                                    url = it.owner.url,
                                    type = it.owner.type,
                                    company = it.owner.company,
                                    location = it.owner.location,
                                    publicRepos = it.owner.publicRepos,
                                    followers = it.owner.followers,
                                    following = it.owner.following,
                                    createdAt = it.owner.createdAt,
                                    updatedAt = it.owner.updatedAt
                                ),
                                htmlUrl = it.htmlUrl,
                                description = it.description
                            )
                        }
                        if (repositories != null) onComplete(repositories)
                    } else {
                        onError(RuntimeException("Incorrect status code $response"))
                    }
                }

                override fun onFailure(call: Call<List<Repositories>>, t: Throwable) {
                    Log.e("tags", "on failure $call")
                    onError(t)
                }
            }
        )
    }
}
package com.skillbox.github.ui.starred_list

import com.skillbox.github.data.AuthRepository
import com.skillbox.github.network.Network
import com.skillbox.github.network.Repositories

class StarredListRepository {

    suspend fun getStarredRepositories(): List<Repositories> {
        return Network.gitHubApi.getStarred("token " + AuthRepository.accessToken)
    }
}
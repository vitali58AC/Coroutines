package com.skillbox.github.ui.current_user

import com.skillbox.github.data.AuthRepository
import com.skillbox.github.network.Network
import com.skillbox.github.network.PatchUser
import com.skillbox.github.network.User

class CurrentUserRepository {

    suspend fun currentUser(): User {
        return Network.gitHubApi.getUser("token " + AuthRepository.accessToken)
    }

    suspend fun patchUserName(name: String): User {
        return Network.gitHubApi.patchName(
            token = "token " + AuthRepository.accessToken,
            name = PatchUser(name)
        )
    }

    suspend fun userFollowing(): List<User> {
        return Network.gitHubApi.getFollowing("token " + AuthRepository.accessToken)
    }
}
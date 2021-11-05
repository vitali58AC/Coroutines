package com.skillbox.github.data

import net.openid.appauth.ResponseTypeValues

object AuthConfig {

    const val AUTH_URI = "https://github.com/login/oauth/authorize"
    const val TOKEN_URI = "https://github.com/login/oauth/access_token"
    const val RESPONSE_TYPE = ResponseTypeValues.CODE
    const val SCOPE = "user,repo"

    const val CLIENT_ID = "23bb3764d4c2fe860c96"
    const val CLIENT_SECRET = "3bde53d5984cb9f2f96a361da47328c8f2e7dd76"
    const val CALLBACK_URL = "skillbox://skillbox.ru/callback"
}
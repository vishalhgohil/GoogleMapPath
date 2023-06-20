package com.app.core


import com.app.data.pojo.User

public interface Session {

    var apiKey: String

    var userSession: String

    var userId: String

    var deviceToken: String

    var user: User?

    val language: String

    fun clearSession()

    companion object {
        const val API_KEY = "api-key"
        const val USER_SESSION = "token"
        const val USER_ID = "USER_ID"
        const val DEVICE_TOKEN = "DEVICE_TOKEN"
        const val DEVICE_TYPE = "A"
        const val LANGUAGE = "accept-language"
    }
}

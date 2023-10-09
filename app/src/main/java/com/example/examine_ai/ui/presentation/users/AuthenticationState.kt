package com.example.examine_ai.ui.presentation.users

import com.example.examine_ai.data.model.User

sealed class AuthenticationState {
    object Unauthenticated : AuthenticationState()
    data class Authenticated(val user: User) : AuthenticationState()
    object AuthenticationFailed : AuthenticationState()
    object Authenticating : AuthenticationState()
}
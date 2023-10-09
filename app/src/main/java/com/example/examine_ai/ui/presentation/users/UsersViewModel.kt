package com.example.examine_ai.ui.presentation.users

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.examine_ai.data.AppDatabase
import com.example.examine_ai.data.model.Paciente
import com.example.examine_ai.data.model.User
import com.example.examine_ai.data.repository.UserRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val userRepository: UserRepository

    private val _loginResult = MutableStateFlow<AuthenticationState?>(null)
    val loginResult: StateFlow<AuthenticationState?> get() = _loginResult

    init {
        val userDao = AppDatabase.getDatabase(application).userDao()
        userRepository = UserRepository(userDao)
    }

    @SuppressLint("SuspiciousIndentation")
    fun login(username: String, password: String)
            = viewModelScope.launch {
        _loginResult.value = AuthenticationState.Authenticating

        try {
        val user = userRepository.login(username, password)
            if (user != null) {
                _loginResult.value = AuthenticationState.Authenticated(user)
            } else {
                _loginResult.value = AuthenticationState.AuthenticationFailed
            }
    }
        catch (e: Exception){
            _loginResult.value = AuthenticationState.AuthenticationFailed
        }
    }


    fun insert(user: User) = viewModelScope.launch(Dispatchers.IO) {
        userRepository.register(user)
    }
}
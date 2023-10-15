package com.example.examine_ai.ui.presentation.users

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.examine_ai.data.AppDatabase
import com.example.examine_ai.data.model.User
import com.example.examine_ai.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val userRepository: UserRepository

    private val _loginResult = MutableStateFlow<AuthenticationState?>(null)
    val loginResult: StateFlow<AuthenticationState?> get() = _loginResult

    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String> = _userName


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

    fun getUserById(id: Int) = viewModelScope.launch(Dispatchers.IO){
        userRepository.getUserById(id)
    }
    fun setUserName(name: String) {
        _userName.value = name
    }
}
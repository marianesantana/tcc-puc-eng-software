package com.example.examine_ai.data.repository

import com.example.examine_ai.data.dao.UserDao
import com.example.examine_ai.data.model.User

class UserRepository(private val userDao: UserDao) {
    suspend fun login(username: String, password: String): User? {
        return userDao.getUser(username, password)
    }
    suspend fun register(user: User) {
        return userDao.insertUser(user)
    }
}
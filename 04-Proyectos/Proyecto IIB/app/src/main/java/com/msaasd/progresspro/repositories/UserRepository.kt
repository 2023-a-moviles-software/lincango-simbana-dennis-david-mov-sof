package com.msaasd.progresspro.repositories

import androidx.lifecycle.LiveData
import com.msaasd.progresspro.models.daos.UserDao
import com.msaasd.progresspro.models.entities.User
import com.msaasd.progresspro.models.entities.relations.UserWithBadges
import com.msaasd.progresspro.models.entities.relations.UserWithTasks

class UserRepository(private val userDao: UserDao) {
    suspend fun insertUser(user: User) {
        userDao.insert(user)
    }

    suspend fun updateUser(user: User) {
        userDao.update(user)
    }

    suspend fun getUser(userId: Int): LiveData<User> {
        return userDao.getUser(userId)
    }

    suspend fun getUserWithTasks(userId: Int): LiveData<UserWithTasks> {
        return userDao.getUserWithTasks(userId)
    }

    suspend fun getUserWithBadges(userId: Int): LiveData<UserWithBadges> {
        return userDao.getUserWithBadges(userId)
    }
}
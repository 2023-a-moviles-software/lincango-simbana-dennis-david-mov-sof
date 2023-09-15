package com.msaasd.progresspro.repositories

import com.msaasd.progresspro.models.daos.UserBadgeCrossRefDao
import com.msaasd.progresspro.models.entities.relations.UserBadgeCrossRef

class UserBadgeCrossRefRepository(private val userBadgeCrossRefDao: UserBadgeCrossRefDao) {

    suspend fun insertUserBadgeCrossRef(userBadgeCrossRef: UserBadgeCrossRef) {
        userBadgeCrossRefDao.insertUserBadgeCrossRef(userBadgeCrossRef)
    }
}
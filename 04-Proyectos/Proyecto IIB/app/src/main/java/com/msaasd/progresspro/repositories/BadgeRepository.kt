package com.msaasd.progresspro.repositories

import androidx.lifecycle.LiveData
import com.msaasd.progresspro.models.daos.BadgeDao
import com.msaasd.progresspro.models.entities.Badge

class BadgeRepository(private val badgeDao: BadgeDao) {

    suspend fun insert(badge: Badge) {
        badgeDao.insert(badge)
    }

    suspend fun update(badge: Badge) {
        badgeDao.update(badge)
    }

    suspend fun deleteBadge(badge: Badge) {
        badgeDao.deleteBadge(badge)
    }

    suspend fun getBadgeById(badgeId: Int): LiveData<Badge> {
        return badgeDao.getBadgeById(badgeId)
    }

    suspend fun getAllBadges(): LiveData<List<Badge>> {
        return badgeDao.getAllBadges()
    }

    suspend fun getPendingBadgesForUser(userId: Int): LiveData<List<Badge>> {
        return badgeDao.getPendingBadgesForUser(userId)
    }

    suspend fun getNextBadgeToUnlock(userExperiencePoints: Int): LiveData<Badge?> {
        return badgeDao.getNextBadgeToUnlock(userExperiencePoints)
    }
}
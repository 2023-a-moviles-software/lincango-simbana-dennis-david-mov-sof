package com.msaasd.progresspro.models.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.msaasd.progresspro.models.entities.Badge

@Dao
interface BadgeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(badge: Badge)

    @Update
    suspend fun update(badge: Badge)

    @Delete
    suspend fun deleteBadge(badge: Badge)

    @Transaction
    @Query("SELECT * FROM badges WHERE badge_id = :badgeId")
    suspend fun getBadgeById(badgeId: Int): LiveData<Badge>

    @Transaction
    @Query("SELECT * FROM badges")
    suspend fun getAllBadges(): LiveData<List<Badge>>

    @Transaction
    @Query(
        "SELECT * FROM badges WHERE badge_id NOT IN" +
                " (SELECT badge_id FROM user_badge_cross_ref WHERE user_id = :userId)"
    )
    suspend fun getPendingBadgesForUser(userId: Int): LiveData<List<Badge>>

    @Query(
        "SELECT * FROM badges WHERE required_points > :userExperiencePoints " +
                "ORDER BY required_points ASC LIMIT 1"
    )
    suspend fun getNextBadgeToUnlock(userExperiencePoints: Int): LiveData<Badge?>
}
package com.msaasd.progresspro.models.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.msaasd.progresspro.models.entities.User
import com.msaasd.progresspro.models.entities.relations.UserWithBadges
import com.msaasd.progresspro.models.entities.relations.UserWithTasks

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg users: User)

    @Update
    suspend fun update(vararg users: User)

    @Transaction
    @Query("SELECT * FROM ${User.TABLE_NAME} LIMIT 1")
    suspend fun getUser(): LiveData<User>

    @Transaction
    @Query("SELECT * FROM ${User.TABLE_NAME} WHERE user_id = :userId")
    suspend fun getUser(userId: Int): LiveData<User>

    @Transaction
    @Query("SELECT * FROM ${User.TABLE_NAME} WHERE user_id = :userId")
    suspend fun getUserWithTasks(userId: Int): LiveData<UserWithTasks>

    @Transaction
    @Query("SELECT * FROM ${User.TABLE_NAME} WHERE user_id = :userId")
    suspend fun getUserWithBadges(userId: Int): LiveData<UserWithBadges>
}
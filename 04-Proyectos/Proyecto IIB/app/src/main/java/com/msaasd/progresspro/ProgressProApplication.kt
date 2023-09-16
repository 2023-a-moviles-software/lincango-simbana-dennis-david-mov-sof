package com.msaasd.progresspro

import android.app.Application
import com.msaasd.progresspro.models.database.ProgressProDatabase
import com.msaasd.progresspro.repositories.BadgeRepository
import com.msaasd.progresspro.repositories.SubtaskRepository
import com.msaasd.progresspro.repositories.TaskRepository
import com.msaasd.progresspro.repositories.UserBadgeCrossRefRepository
import com.msaasd.progresspro.repositories.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class ProgressProApplication: Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { ProgressProDatabase.getDatabase(this, applicationScope) }
    val badgeRepository by lazy { BadgeRepository(database.badgeDao()) }
    val subtaskRepository by lazy { SubtaskRepository(database.subtaskDao()) }
    val taskRepository by lazy { TaskRepository(database.taskDao()) }
    val userBadgeCrossRefRepository by lazy { UserBadgeCrossRefRepository(database.userBadgeCrossRefDao()) }
    val userRepository by lazy { UserRepository(database.userDao()) }
}
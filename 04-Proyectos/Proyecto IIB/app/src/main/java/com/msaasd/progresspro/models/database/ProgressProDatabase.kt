package com.msaasd.progresspro.models.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.msaasd.progresspro.models.converters.LocalDateConverter
import com.msaasd.progresspro.models.converters.LocalDateTimeConverter
import com.msaasd.progresspro.models.converters.LocalTimeConverter
import com.msaasd.progresspro.models.converters.TaskStateConverter
import com.msaasd.progresspro.models.daos.BadgeDao
import com.msaasd.progresspro.models.daos.SubtaskDao
import com.msaasd.progresspro.models.daos.TaskDao
import com.msaasd.progresspro.models.daos.UserBadgeCrossRefDao
import com.msaasd.progresspro.models.daos.UserDao
import com.msaasd.progresspro.models.database.Badges.BADGES
import com.msaasd.progresspro.models.entities.Badge
import com.msaasd.progresspro.models.entities.Subtask
import com.msaasd.progresspro.models.entities.Task
import com.msaasd.progresspro.models.entities.User
import com.msaasd.progresspro.models.entities.relations.UserBadgeCrossRef
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(
    version = 1,
    entities = [
        Badge::class,
        Subtask::class,
        Task::class,
        User::class
    ]
)
@TypeConverters(
    LocalDateConverter::class,
    LocalDateTimeConverter::class,
    LocalTimeConverter::class,
    TaskStateConverter::class,
    UserBadgeCrossRef::class
)
abstract class ProgressProDatabase : RoomDatabase() {
    companion object {
        @Volatile
        private var INSTANCE: ProgressProDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): ProgressProDatabase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    ProgressProDatabase::class.java,
                    "progresspro_db"
                ).addCallback(
                    ProgressProDatabaseCallback(scope)
                ).build().also {
                    INSTANCE = it
                }
            }
        }
    }

    abstract fun badgeDao(): BadgeDao

    abstract fun userDao(): UserDao

    abstract fun taskDao(): TaskDao

    abstract fun subtaskDao(): SubtaskDao

    abstract fun userBadgeCrossRefDao(): UserBadgeCrossRefDao

    private class ProgressProDatabaseCallback(private val scope: CoroutineScope) :
        RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateBadges(database.badgeDao())
                    database.userDao().insert(
                        User(
                            firstName = "Adrián",
                            lastName = "Egüez"
                        )
                    )
                }
            }
        }

        private suspend fun populateBadges(badgeDao: BadgeDao) {
            BADGES.forEach {
                badgeDao.insert(it)
            }
        }
    }
}
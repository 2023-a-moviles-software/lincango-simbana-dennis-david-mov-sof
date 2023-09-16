package com.msaasd.progresspro.models.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.msaasd.progresspro.models.entities.User.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("user_id") val userId: Int = 0,
    @ColumnInfo("first_name") var firstName: String,
    @ColumnInfo("last_name") var lastName: String,
    @ColumnInfo("experience_points") var experiencePoints: Int = 0
) {
    companion object {
        const val TABLE_NAME = "users"
    }

    fun completeTask(task: Task) {
        task.setCompleted()
        experiencePoints += task.points
    }
}
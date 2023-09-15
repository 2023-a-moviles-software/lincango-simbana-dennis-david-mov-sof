package com.msaasd.progresspro.models.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.msaasd.progresspro.models.converters.LocalDateConverter
import com.msaasd.progresspro.models.converters.LocalDateTimeConverter
import com.msaasd.progresspro.models.converters.TaskStateConverter
import com.msaasd.progresspro.models.entities.Task.Companion.TABLE_NAME
import java.time.LocalDate
import java.time.LocalDateTime

@Entity(tableName = TABLE_NAME)
data class Task(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "task_id") val taskId: Int = 0,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "description") var description: String? = null,
    @TypeConverters(TaskStateConverter::class) @ColumnInfo(name = "state") var state: TaskState = TaskState.CREATED,
    @ColumnInfo(name = "is_pinned") var isPinned: Boolean,
    @TypeConverters(LocalDateTimeConverter::class) @ColumnInfo(name = "entry_date_time") val entryDateTime: LocalDateTime,
    @TypeConverters(LocalDateConverter::class) @ColumnInfo(name = "due_date") var dueDate: LocalDate? = null,
    @ColumnInfo(name = "points") val points: Int,
    @TypeConverters(LocalDateTimeConverter::class) @ColumnInfo(name = "completed_date_time") var completedDateTime: LocalDateTime? = null,
    @ColumnInfo(name = "user_id") val userId: Int
) {
    companion object {
        const val TABLE_NAME = "tasks"
    }

    fun setCompleted() {
        completedDateTime = LocalDateTime.now()
        state = TaskState.DONE
    }
}
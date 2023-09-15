package com.msaasd.progresspro.models.converters

import androidx.room.TypeConverter
import com.msaasd.progresspro.models.entities.TaskState

class TaskStateConverter {
    @TypeConverter
    fun fromTaskState(taskState: TaskState): String {
        return taskState.name
    }

    @TypeConverter
    fun toTaskState(taskState: String): TaskState {
        return enumValueOf<TaskState>(taskState)
    }
}
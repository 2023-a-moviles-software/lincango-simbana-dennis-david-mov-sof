package com.msaasd.progresspro.models.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.msaasd.progresspro.models.entities.Subtask.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class Subtask(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "subtask_id") val subtaskId: Int = 0,
    @ColumnInfo(name = "description") var descriptor: String,
    @ColumnInfo(name = "is_complete") var isComplete: Boolean,
    @ColumnInfo(name = "task_id") val taskId: Int
) {
    companion object {
        const val TABLE_NAME = "subtasks"
    }

    fun markAsDone() {
        isComplete = true
    }    
}
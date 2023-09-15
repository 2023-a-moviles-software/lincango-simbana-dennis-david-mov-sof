package com.msaasd.progresspro.models.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.msaasd.progresspro.models.entities.Subtask
import com.msaasd.progresspro.models.entities.Task

data class TaskWithSubtasks(
    @Embedded val task: Task,
    @Relation(
        parentColumn = "taskId",
        entityColumn = "taskId"
    )
    val subtasks: List<Subtask>
)

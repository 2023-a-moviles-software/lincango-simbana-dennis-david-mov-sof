package com.msaasd.progresspro.models.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.msaasd.progresspro.models.entities.Task
import com.msaasd.progresspro.models.entities.User

data class UserWithTasks(
    @Embedded val user: User,
    @Relation(
        parentColumn = "userId",
        entityColumn = "userId"
    )
    var tasks: List<Task>
)

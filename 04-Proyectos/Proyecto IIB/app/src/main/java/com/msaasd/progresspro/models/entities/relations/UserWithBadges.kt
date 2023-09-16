package com.msaasd.progresspro.models.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.msaasd.progresspro.models.entities.Badge
import com.msaasd.progresspro.models.entities.User

data class UserWithBadges(
    @Embedded val user: User,
    @Relation(
        parentColumn = "userId",
        entityColumn = "badgeId",
        associateBy = Junction(UserBadgeCrossRef::class)
    )
    val badges: List<Badge>
)

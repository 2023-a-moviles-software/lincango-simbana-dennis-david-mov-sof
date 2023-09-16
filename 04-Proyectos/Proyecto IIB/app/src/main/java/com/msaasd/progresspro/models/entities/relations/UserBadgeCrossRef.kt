package com.msaasd.progresspro.models.entities.relations

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.msaasd.progresspro.models.entities.relations.UserBadgeCrossRef.Companion.TABLE_NAME

@Entity(
    tableName = TABLE_NAME,
    primaryKeys = ["user_id", "badge_id"]
)
data class UserBadgeCrossRef(
    @ColumnInfo(name = "user_id") val userId: Int,
    @ColumnInfo(name = "badge_id") val badgeId: Int
) {
    companion object {
        const val TABLE_NAME = "user_badge_cross_ref"
    }
}
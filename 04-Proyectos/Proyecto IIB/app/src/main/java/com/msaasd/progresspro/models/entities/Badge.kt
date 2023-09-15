package com.msaasd.progresspro.models.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.msaasd.progresspro.models.entities.Badge.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class Badge(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "badge_id") val badgeId: Int = 0,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "description") var description: String?,
    @ColumnInfo(name = "required_points") val requiredPoints: Int
) {
    companion object {
        const val TABLE_NAME = "badges"
    }
}
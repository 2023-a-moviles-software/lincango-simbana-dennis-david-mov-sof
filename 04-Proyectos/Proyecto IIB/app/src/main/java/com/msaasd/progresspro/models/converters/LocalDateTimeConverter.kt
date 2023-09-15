package com.msaasd.progresspro.models.converters

import androidx.room.TypeConverter
import com.msaasd.progresspro.util.extensions.toFormattedString
import com.msaasd.progresspro.util.extensions.toLocalDateTime
import java.time.LocalDateTime

class LocalDateTimeConverter {
    @TypeConverter
    fun fromTimestamp(value: String): LocalDateTime {
        return value.toLocalDateTime()!!
    }

    @TypeConverter
    fun dateTimeToTimestamp(dateTime: LocalDateTime): String {
        return dateTime.toFormattedString()
    }
}
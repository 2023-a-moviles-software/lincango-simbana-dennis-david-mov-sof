package com.msaasd.progresspro.models.converters

import androidx.room.TypeConverter
import com.msaasd.progresspro.util.extensions.toFormattedString
import com.msaasd.progresspro.util.extensions.toLocalTime
import java.time.LocalTime

class LocalTimeConverter {
    @TypeConverter
    fun fromTimestamp(value: String): LocalTime {
        return value.toLocalTime()!!
    }

    @TypeConverter
    fun timeToTimestamp(time: LocalTime): String {
        return time.toFormattedString()
    }
}
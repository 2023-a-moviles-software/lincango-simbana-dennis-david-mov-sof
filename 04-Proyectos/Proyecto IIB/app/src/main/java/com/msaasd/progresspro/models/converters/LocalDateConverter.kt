package com.msaasd.progresspro.models.converters

import androidx.room.TypeConverter
import com.msaasd.progresspro.util.extensions.toFormattedString
import com.msaasd.progresspro.util.extensions.toLocalDate
import java.time.LocalDate

class LocalDateConverter {
    @TypeConverter
    fun fromTimestamp(value: String): LocalDate {
        return value.toLocalDate()!!
    }

    @TypeConverter
    fun dateToTimestamp(date: LocalDate): String {
        return date.toFormattedString()
    }
}
package com.msaasd.progresspro.util.extensions

import com.msaasd.progresspro.util.Constants
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

fun String.toLocalDate(): LocalDate? {
    return LocalDate.parse(this, Constants.DateTimeConstants.DATE_FORMATTER)
}

fun String.toLocalTime(): LocalTime? {
    return LocalTime.parse(this, Constants.DateTimeConstants.TIME_FORMATTER)
}

fun String.toLocalDateTime(): LocalDateTime? {
    return LocalDateTime.parse(this, Constants.DateTimeConstants.DATE_TIME_FORMATTER)
}
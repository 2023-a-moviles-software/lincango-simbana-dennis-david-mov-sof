package com.msaasd.progresspro.util.extensions

import com.msaasd.progresspro.util.Constants
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

fun LocalDate.toFormattedString(): String {
    return this.format(Constants.DateTimeConstants.DATE_FORMATTER)
}

fun LocalTime.toFormattedString(): String {
    return this.format(Constants.DateTimeConstants.TIME_FORMATTER)
}

fun LocalDateTime.toFormattedString(): String {
    return this.format(Constants.DateTimeConstants.DATE_TIME_FORMATTER)
}
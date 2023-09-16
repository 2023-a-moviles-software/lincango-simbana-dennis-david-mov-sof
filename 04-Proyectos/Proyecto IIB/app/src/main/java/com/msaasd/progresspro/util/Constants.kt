package com.msaasd.progresspro.util

import java.time.format.DateTimeFormatter

object Constants {
    object DateTimeConstants {
        //Formats
        private const val DATE_FORMAT = "dd/MM/yyyy"
        private const val TIME_FORMAT = "HH:mm"
        private const val DATE_TIME_FORMAT = "$DATE_FORMAT $TIME_FORMAT"

        //Formatters
        val DATE_FORMATTER: DateTimeFormatter =
            DateTimeFormatter.ofPattern(DATE_FORMAT)
        val TIME_FORMATTER: DateTimeFormatter =
            DateTimeFormatter.ofPattern(TIME_FORMAT)
        val DATE_TIME_FORMATTER: DateTimeFormatter =
            DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)
    }

    object ExtraDataConstants {
        const val USER_ID = "userId"
        const val TASK_ID = "taskId"
    }
}
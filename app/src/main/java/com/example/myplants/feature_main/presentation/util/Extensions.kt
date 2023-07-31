package com.example.myplants.feature_main.presentation.util

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.ZoneId
import java.time.temporal.TemporalAdjusters
import java.util.Date

fun LocalDate.getNext(dayOfWeek: DayOfWeek): LocalDate {
    return this.with(TemporalAdjusters.next(dayOfWeek))
}

fun LocalDate.toLong(zoneId: ZoneId): Long {
    return Date.from(this.atStartOfDay(zoneId).toInstant()).time
}

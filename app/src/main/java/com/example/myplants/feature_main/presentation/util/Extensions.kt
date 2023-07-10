package com.example.myplants.feature_main.presentation.util

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.ZoneId
import java.time.temporal.TemporalAdjusters
import java.util.Date

fun LocalDate.getNext(dayOfWeek: DayOfWeek): LocalDate {
    return this.with(TemporalAdjusters.next(dayOfWeek))
}

fun LocalDate.asDate(zoneId: ZoneId): Date {
    return Date.from(this.atStartOfDay(zoneId).toInstant())
}

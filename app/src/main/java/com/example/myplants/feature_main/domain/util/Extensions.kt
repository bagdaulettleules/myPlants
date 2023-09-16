package com.example.myplants.feature_main.domain.util

import com.example.myplants.feature_main.domain.model.Plant
import com.example.myplants.feature_main.domain.model.Task
import java.time.DayOfWeek
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.temporal.TemporalAdjusters
import java.util.Date

fun Plant.generateNewTask(plantId: Long, startDate: Long): Task {
    val localDate = Instant.ofEpochMilli(startDate).atZone(ZoneId.systemDefault()).toLocalDate()
    val nextDayOfWeek = this.waterDays.getNext(localDate.dayOfWeek)

    return Task(
        plantId = plantId,
        dueDateTs = localDate.getNext(nextDayOfWeek).toLong(ZoneId.systemDefault()),
        updateTs = System.currentTimeMillis(),
        isDone = false
    )
}

fun List<DayOfWeek>.getNext(dayOfWeek: DayOfWeek): DayOfWeek {
    return this.sorted()
        .firstOrNull { it >= dayOfWeek }
        ?: this[0]
}

fun LocalDate.getNext(dayOfWeek: DayOfWeek): LocalDate {
    return this.with(TemporalAdjusters.next(dayOfWeek))
}

fun LocalDate.toLong(zoneId: ZoneId): Long {
    return Date.from(this.atStartOfDay(zoneId).toInstant()).time
}

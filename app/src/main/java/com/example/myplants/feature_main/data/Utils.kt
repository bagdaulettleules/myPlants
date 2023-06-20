package com.example.myplants.feature_main.data

import java.time.DayOfWeek

fun getUpcomingWeek(startDayOfWeek: DayOfWeek): Array<DayOfWeek> {
    val daysOfWeek = enumValues<DayOfWeek>()
    val s = daysOfWeek.size - 1
    val step = startDayOfWeek.value % (s + 1)
    replace(daysOfWeek, 0, s - step)
    replace(daysOfWeek, s - step + 1, s)
    replace(daysOfWeek, 0, s)
    return daysOfWeek
}

fun replace(arr: Array<DayOfWeek>, startIdx: Int, endIdx: Int) {
    var l = startIdx
    var r = endIdx
    while (l < r) {
        val tmp = arr[l]
        arr[l] = arr[r]
        arr[r] = tmp
        l++
        r--
    }
}
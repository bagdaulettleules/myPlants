package com.example.myplants.feature_main.domain.util

sealed class FetchType {
    object Upcoming: FetchType()
    object Missed: FetchType()
    object History: FetchType()
}

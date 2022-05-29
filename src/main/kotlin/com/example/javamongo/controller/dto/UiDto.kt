package com.example.javamongo.controller.dto

import com.example.javamongo.data.entity.emuns.IntervalEnum

interface UiDto {
    fun String.toMap(): Map<IntervalEnum, Int> =
        split('.').mapIndexed { index, s -> IntervalEnum.values()[index] to s.toInt() }
            .filter { it.second > 0 }.toMap()
}
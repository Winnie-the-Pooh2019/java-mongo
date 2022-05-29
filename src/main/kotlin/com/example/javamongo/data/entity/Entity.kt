package com.example.javamongo.data.entity

import com.example.javamongo.controller.dto.UiDto
import com.example.javamongo.data.entity.emuns.IntervalEnum
import org.bson.types.ObjectId

interface Entity {
    val id: ObjectId

    fun toUi(): UiDto

    fun Map<IntervalEnum, Int>.stringify(): String = IntervalEnum.values()
        .map { it.ordinal to if (it in this.keys) this[it]!!.toString() else "0" }.sortedBy { it.first }
        .joinToString(".") { it.second }
}
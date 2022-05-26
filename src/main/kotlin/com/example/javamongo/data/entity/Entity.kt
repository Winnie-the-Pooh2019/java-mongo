package com.example.javamongo.data.entity

import com.example.javamongo.controller.dto.UiDto
import org.bson.types.ObjectId

interface Entity {
    val id: ObjectId

    fun toUi(): UiDto
}
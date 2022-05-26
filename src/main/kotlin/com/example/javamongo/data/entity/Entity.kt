package com.example.javamongo.data.entity

import com.example.javamongo.controller.dto.UiDto

interface Entity {
    fun toUi(): UiDto
}
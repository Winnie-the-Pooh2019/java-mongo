package com.example.javamongo.data.entity.ersaz

import com.example.javamongo.data.entity.emuns.IntervalEnum
import com.google.gson.annotations.SerializedName

data class Technology(
    val description: String,
    @SerializedName(value = "prepare_time")
    val prepareTime: Map<IntervalEnum, Int>,
    val resources: List<ResourceTechnology>
)

package com.example.javamongo.data.entity.emuns

enum class OrderStatus {
    NO_COMPONENTS,
    IN_PROGRESS,
    DONE,
    PICKED;

    override fun toString(): String = this.name
}
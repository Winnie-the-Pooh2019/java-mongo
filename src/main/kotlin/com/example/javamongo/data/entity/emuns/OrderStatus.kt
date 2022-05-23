package com.example.javamongo.data.entity.emuns

enum class OrderStatus {
    IN_PROGRESS,
    DONE,
    PICKED;

    override fun toString(): String = this.name
}
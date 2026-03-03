package com.jemy.thamanya.data.models

enum class ViewType(val backendValue: String) {
    TWO_LINES_GRID("2_lines_grid"),
    SQUARE("square"),
    BIG_SQUARE("big_square"),
    QUEUE("queue");

    companion object {
        fun from(value: String?): ViewType {
            return values().find { it.backendValue == value }
                ?: ViewType.BIG_SQUARE
        }
    }
}
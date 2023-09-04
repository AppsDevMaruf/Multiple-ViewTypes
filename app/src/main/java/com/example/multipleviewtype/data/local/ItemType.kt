package com.example.multipleviewtype.data.local

sealed class ItemType {
    abstract val id: Long

    data class CircleItem(override val id: Long, val data: CircleData) : ItemType()
    data class SquareItem(override val id: Long, val data: SquareData) : ItemType()
    data class RectangularItem(override val id: Long, val data: RectangularData) : ItemType()
}

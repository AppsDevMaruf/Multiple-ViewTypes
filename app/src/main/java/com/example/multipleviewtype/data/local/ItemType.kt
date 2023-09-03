package com.example.multipleviewtype.data.local

sealed class ItemType {
    data class CircleItem(val data: CircleData) : ItemType()
    data class SquareItem(val data: SquareData) : ItemType()
    data class RectangularItem(val data: RectangularData) : ItemType()
}
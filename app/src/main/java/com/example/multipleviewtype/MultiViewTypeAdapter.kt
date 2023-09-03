package com.example.multipleviewtype

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.multipleviewtype.data.local.ItemType

class MultiViewTypeAdapter :
    RecyclerView.Adapter<MultiViewTypeAdapter.ItemViewHolder>() {
    companion object {
        private const val CIRCLE_VIEW_TYPE = 0
        private const val SQUARE_VIEW_TYPE = 1
        private const val RECTANGULAR_VIEW_TYPE = 2
    }

    private val adapterData = mutableListOf<ItemType>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layout = when (viewType) {
            CIRCLE_VIEW_TYPE -> R.layout.item_circle
            SQUARE_VIEW_TYPE -> R.layout.item_square
            RECTANGULAR_VIEW_TYPE -> R.layout.item_rectangular
            else -> throw IllegalArgumentException("Invalid type")
        }

        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)

        return ItemViewHolder(view)
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(adapterData[position])
    }

    override fun getItemCount(): Int {
        return adapterData.size
    }


    override fun getItemViewType(position: Int): Int {
        return when (adapterData[position]) {
            is ItemType.CircleItem -> CIRCLE_VIEW_TYPE
            is ItemType.SquareItem -> SQUARE_VIEW_TYPE
            is ItemType.RectangularItem -> RECTANGULAR_VIEW_TYPE
            else -> throw IllegalArgumentException("Invalid item type")
        }
    }

    fun setData(data: List<ItemType>) {
        adapterData.apply {
            clear()
            addAll(data)
        }
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private fun bindCircle(item: ItemType.CircleItem) {
            //Do your view assignment here from the data model

        }

        private fun bindSquare(item: ItemType.SquareItem) {

        }

        private fun bindRectangular(item: ItemType.RectangularItem) {

        }

        fun bind(dataModel: ItemType) {
            when (dataModel) {
                is ItemType.CircleItem -> bindCircle(dataModel)
                is ItemType.SquareItem -> bindSquare(dataModel)
                is ItemType.RectangularItem -> bindRectangular(dataModel)

            }
        }
    }



}

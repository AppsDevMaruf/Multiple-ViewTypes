package com.example.multipleviewtype

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.multipleviewtype.data.local.ItemType
import com.example.multipleviewtype.databinding.ItemCircleBinding
import com.example.multipleviewtype.databinding.ItemRectangularBinding
import com.example.multipleviewtype.databinding.ItemSquareBinding


class MultiViewTypeAdapter() : ListAdapter<ItemType, MultiViewTypeAdapter.ItemViewHolder>(ItemDiffCallback()){
    companion object {
        private const val CIRCLE_VIEW_TYPE = 0
        private const val SQUARE_VIEW_TYPE = 1
        private const val RECTANGULAR_VIEW_TYPE = 2
    }

    private val adapterData = mutableListOf<ItemType>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = when (viewType) {
            CIRCLE_VIEW_TYPE -> ItemCircleBinding.inflate(inflater, parent, false)
            SQUARE_VIEW_TYPE -> ItemSquareBinding.inflate(inflater, parent, false)
            RECTANGULAR_VIEW_TYPE -> ItemRectangularBinding.inflate(inflater, parent, false)
            else -> throw IllegalArgumentException("Invalid type")
        }


        return ItemViewHolder(binding)
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
            submitList(data)
        }
    }

    class ItemViewHolder(private val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {


        fun bind(dataModel: ItemType) {
            when (dataModel) {
                is ItemType.CircleItem -> {
                    val circleBinding = binding as ItemCircleBinding
                    circleBinding.circleText.text = dataModel.data.title
                }

                is ItemType.SquareItem -> {
                    val squareBinding = binding as ItemSquareBinding
                    squareBinding.squareText.text = dataModel.data.title
                }

                is ItemType.RectangularItem -> {
                    val rectangularBinding = binding as ItemRectangularBinding
                    rectangularBinding.rectangularText.text = dataModel.data.title

                }
            }
        }
    }
    class ItemDiffCallback : DiffUtil.ItemCallback<ItemType>() {
        override fun areItemsTheSame(oldItem: ItemType, newItem: ItemType): Boolean {
            // Check if items have the same unique ID
            return oldItem.id == newItem.id
            //unresolved the id
        }

        override fun areContentsTheSame(oldItem: ItemType, newItem: ItemType): Boolean {
            // Implement logic to check if the content of items is the same
            return oldItem == newItem
        }
    }




}

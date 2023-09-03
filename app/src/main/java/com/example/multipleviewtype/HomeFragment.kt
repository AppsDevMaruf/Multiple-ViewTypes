package com.example.multipleviewtype

import com.example.multipleviewtype.base.BaseFragment
import com.example.multipleviewtype.data.local.CircleData
import com.example.multipleviewtype.data.local.ItemType
import com.example.multipleviewtype.data.local.RectangularData
import com.example.multipleviewtype.data.local.SquareData
import com.example.multipleviewtype.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val dataAdapter: MultiViewTypeAdapter by lazy {
        MultiViewTypeAdapter()
    }


    override fun getFragmentView(): Int {
        return R.layout.fragment_home
    }

    override fun configUi() {


        dataAdapter.setData(getMockData())
        binding.recyclerView.apply {
            hasFixedSize()
            adapter = dataAdapter
        }

    }

    private fun getMockData(): List<ItemType> = listOf(
        ItemType.CircleItem(data = CircleData("circle")),
        ItemType.SquareItem(data = SquareData("square")),
        ItemType.RectangularItem(data = RectangularData("rectangular"))
    )

}
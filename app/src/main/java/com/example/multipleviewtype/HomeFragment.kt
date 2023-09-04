package com.example.multipleviewtype

import androidx.fragment.app.viewModels
import com.example.multipleviewtype.base.BaseFragment
import com.example.multipleviewtype.data.local.CircleData
import com.example.multipleviewtype.data.local.ItemType
import com.example.multipleviewtype.data.local.RectangularData
import com.example.multipleviewtype.data.local.SquareData
import com.example.multipleviewtype.data.network.ResponsePostData
import com.example.multipleviewtype.databinding.FragmentHomeBinding
import com.example.multipleviewtype.utils.NetworkResult
import com.example.multipleviewtype.viewmodel.PostViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val postDataViewModel by viewModels<PostViewModel>()
    private val dataAdapter: MultiViewTypeAdapter by lazy {
        MultiViewTypeAdapter()
    }


    override fun getFragmentView(): Int {
        return R.layout.fragment_home
    }

    override fun configUi() {
        postDataViewModel.getPostsVM()


    }

    override fun binObserver() {
        postDataViewModel.responsePostDataList.observe(viewLifecycleOwner) {
            //binding.progressBar.gone()
            when (it) {
                is NetworkResult.Success -> {

                    it.data?.let { postDataItem ->
                        val serverResponse: List<ResponsePostData.ResponsePostDataItem> = postDataItem

                        val itemTypeList = serverResponse.map { responseItem ->
                            when (responseItem.title?.length ?: 0) {
                                in 0 until 20 -> {
                                    val circleData = CircleData(
                                        responseItem.id,
                                        responseItem.title,
                                        responseItem.body,
                                        responseItem.userId
                                    )
                                    ItemType.CircleItem(1, circleData)
                                }
                                in 20 until 40 -> {
                                    val squareData = SquareData(
                                        responseItem.id,
                                        responseItem.title,
                                        responseItem.body,
                                        responseItem.userId
                                    )
                                    ItemType.SquareItem(1, squareData)
                                }
                                else -> {
                                    val rectangularData = RectangularData(
                                        responseItem.id,
                                        responseItem.title,
                                        responseItem.body,
                                        responseItem.userId
                                    )
                                    ItemType.RectangularItem(1, rectangularData)
                                }
                            }
                        }

                        dataAdapter.setData(itemTypeList)
                        binding.recyclerView.apply {
                            setHasFixedSize(true)
                            adapter = dataAdapter
                        }
                    }


                }

                is NetworkResult.Error -> {

                }

                is NetworkResult.Loading -> {
                    //binding.progressBar.show()
                }
            }
        }
    }


}
package com.example.multipleviewtype.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.multipleviewtype.api.NoConnectivityException
import com.example.multipleviewtype.data.network.ResponsePostData
import com.example.multipleviewtype.repos.PublicRepository
import com.example.multipleviewtype.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val publicRepository: PublicRepository
) : ViewModel() {

    // Variables for response data
     val responsePostDataList = MutableLiveData<NetworkResult<List<ResponsePostData.ResponsePostDataItem>>>()

    // Common function to handle API calls
    private fun <T> handleApiCall(
        apiCall: suspend () -> Response<T>,
        resultLiveData: MutableLiveData<NetworkResult<T>>
    ) {
        resultLiveData.postValue(NetworkResult.Loading())
        viewModelScope.launch {
            try {
                val response = apiCall()
                if (response.isSuccessful && response.body() != null) {
                    resultLiveData.postValue(NetworkResult.Success(response.body()!!))
                } else if (response.errorBody() != null) {
                    val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
                    resultLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
                }
            } catch (noInternetException: NoConnectivityException) {
                resultLiveData.postValue(noInternetException.localizedMessage?.let {
                    NetworkResult.Error(it)
                })
            }
        }
    }

    // API call methods
    fun getPostsVM() {
        handleApiCall(
            apiCall = { publicRepository.postsRepo() },
            resultLiveData = responsePostDataList
        )
    }

}
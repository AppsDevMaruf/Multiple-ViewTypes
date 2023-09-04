package com.example.multipleviewtype.data.network
import com.google.gson.annotations.SerializedName

class ResponsePostData : ArrayList<ResponsePostData.ResponsePostDataItem>(){
    data class ResponsePostDataItem(
        @SerializedName("body")
        val body: String?,
        @SerializedName("id")
        val id: Int?,
        @SerializedName("title")
        val title: String?,
        @SerializedName("userId")
        val userId: Int?
    )
}
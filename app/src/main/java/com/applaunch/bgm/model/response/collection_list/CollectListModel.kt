package com.applaunch.bgm.model.response.collection_list

import com.applaunch.appbase.model_base.BaseResponse
import com.applaunch.bgm.model.dto.collection_list.CollectListData
import com.google.gson.annotations.SerializedName

data class CollectListModel(
    @SerializedName("data")
    val data: ArrayList<CollectListData>
) : BaseResponse()

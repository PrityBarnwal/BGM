package com.applaunch.bgm.model.response.meet_model

import com.applaunch.appbase.model_base.BaseResponse
import com.applaunch.bgm.model.dto.meet_data.MeetDataModel
import com.google.gson.annotations.SerializedName

data class MeetModel( @SerializedName("data")
                      val data: ArrayList<MeetDataModel>
): BaseResponse()
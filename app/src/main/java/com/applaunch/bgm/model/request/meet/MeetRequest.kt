package com.applaunch.bgm.model.request.meet

import com.google.gson.annotations.SerializedName

data class MeetRequest(
    @SerializedName("type")
    var type:String="",
    @SerializedName("title")
    var title:String=""
)

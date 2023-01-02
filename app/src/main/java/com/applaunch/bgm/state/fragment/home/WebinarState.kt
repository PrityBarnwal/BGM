package com.applaunch.bgm.state.fragment.home

import com.applaunch.bgm.adapter.recycler.adapter.WebinarAdapter
import com.applaunch.bgm.model.dto.webinar_data.WebinarDataModel

sealed class WebinarState{
    object Init:WebinarState()
    data class SUCCESS(val data: WebinarDataModel) : WebinarState()
    data class ERROR(val msg: String) : WebinarState()
    data class WebinarList(val adapter: WebinarAdapter): WebinarState()
}

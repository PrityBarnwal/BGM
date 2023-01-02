package com.applaunch.bgm.state.fragment.settings

import com.applaunch.appbase.model_base.BaseResponse

sealed class SettingContactFormState {
    object Init: SettingContactFormState()
    data class SUCCESS(val data:BaseResponse):SettingContactFormState()
    data class ERROR(val msg:String):SettingContactFormState()
}
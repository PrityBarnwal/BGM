package com.applaunch.bgm.state.fragment.settings

import com.applaunch.bgm.model.response.setting_model.ContactSupportModel

sealed class SettingContactSupportState{
    object Init: SettingContactSupportState()
    data class SUCCESS(val data: ContactSupportModel): SettingContactSupportState()
    data class ERROR(val msg:String): SettingContactSupportState()
}
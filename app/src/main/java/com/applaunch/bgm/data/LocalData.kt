package com.applaunch.bgm.data

import android.content.Context
import com.applaunch.bgm.R
import com.applaunch.bgm.model.dto.setting_data.SettingsModel

object LocalData {

    fun getSettingList(context: Context): ArrayList<SettingsModel> {
        val settingList = ArrayList<SettingsModel>()
        settingList.add(
            SettingsModel(
                1,
                R.drawable.ic_contact,
                context.getString(R.string.contact)
            )
        )
        settingList.add(
            SettingsModel(
                2,
                R.drawable.ic_term_condition,
                context.getString(R.string.terms_condition)
            )
        )
        settingList.add(
            SettingsModel(
                3,
                R.drawable.ic_data_privacy,
                context.getString(R.string.data_privacy)
            )
        )
        settingList.add(
            SettingsModel(
                4,
                R.drawable.ic_app_reset,
                context.getString(R.string.reset_app)
            )
        )
        return settingList

    }
}
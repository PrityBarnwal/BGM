package com.applaunch.bgm.viewmodel.fragment.setting

import android.os.Bundle
import com.applaunch.appbase.viewmodel_base.BaseViewModel
import com.applaunch.bgm.adapter.recycler.adapter.SettingAdapter
import com.applaunch.bgm.model.dto.setting_data.SettingsModel
import com.applaunch.bgm.state.fragment.settings.SettingFragmentState
import com.applaunch.bgm.utils.ListenerConstant.SETTING
import doNothing

class SettingFragmentViewModel : BaseViewModel<SettingFragmentState>() {

    lateinit var settingAdapter: SettingAdapter

    var settingFragmentState: SettingFragmentState = SettingFragmentState.Init
        set(value) {
            field = value
            publishState(settingFragmentState)
        }

    override fun onInitialized(bundle: Bundle?) {
      doNothing()
    }

    fun initSettingList(dataList: ArrayList<SettingsModel>) {
        settingAdapter = SettingAdapter(list = dataList, this)
        settingFragmentState = SettingFragmentState.SettingList(settingAdapter)

        settingAdapter.onItemClick = { position, _, type ->
            when (type) {
                SETTING -> {
                    when (position) {
                        0 -> settingFragmentState = SettingFragmentState.NavigateContact()
                        1 -> settingFragmentState = SettingFragmentState.NavigateTermCondition
                        2 -> settingFragmentState = SettingFragmentState.NavigateDataPrivacy
                        3 -> settingFragmentState = SettingFragmentState.NavigateResetApp
                    }
                }
            }
        }
    }
}
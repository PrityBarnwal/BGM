package com.applaunch.bgm.state.fragment.settings

import com.applaunch.bgm.adapter.recycler.adapter.SettingAdapter

sealed class SettingFragmentState{
    object Init:SettingFragmentState()
   class NavigateContact : SettingFragmentState()
    object NavigateTermCondition:SettingFragmentState()
    object NavigateDataPrivacy:SettingFragmentState()
    object NavigateResetApp:SettingFragmentState()
    data class SettingList(var adapter: SettingAdapter): SettingFragmentState()
}

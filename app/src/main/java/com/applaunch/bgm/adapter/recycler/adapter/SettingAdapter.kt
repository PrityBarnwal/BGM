package com.applaunch.bgm.adapter.recycler.adapter

import android.view.ViewGroup
import com.applaunch.appbase.adapter_base.BaseRecyclerAdapter
import com.applaunch.bgm.R
import com.applaunch.bgm.adapter.recycler.viewholder.SettingViewHolder
import com.applaunch.bgm.databinding.ItemListSettingsBinding
import com.applaunch.bgm.model.dto.setting_data.SettingsModel
import com.applaunch.bgm.viewmodel.fragment.setting.SettingFragmentViewModel

class SettingAdapter(
    list: MutableList<SettingsModel>,
    var settingFragmentViewModel: SettingFragmentViewModel,
) : BaseRecyclerAdapter<SettingsModel, SettingViewHolder>(list) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingViewHolder {
        return SettingViewHolder(
            inflateDataBinding(R.layout.item_list_settings, parent) as ItemListSettingsBinding,
            settingFragmentViewModel
        )
    }
}
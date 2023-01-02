package com.applaunch.bgm.adapter.recycler.viewholder

import com.applaunch.appbase.adapter_base.BaseViewHolder
import com.applaunch.bgm.databinding.ItemListSettingsBinding
import com.applaunch.bgm.model.dto.setting_data.SettingsModel
import com.applaunch.bgm.utils.ListenerConstant.SETTING
import com.applaunch.bgm.viewmodel.fragment.setting.SettingFragmentViewModel

class SettingViewHolder(var binding: ItemListSettingsBinding,
                        var viewModel: SettingFragmentViewModel,
):BaseViewHolder<SettingsModel,ItemListSettingsBinding>(binding) {
    override fun populateData(data: SettingsModel, position: Int) {

        binding.settingsList = data

        binding.clSetting.setOnClickListener{
            viewModel.settingAdapter.onItemClick?.invoke(position, data, SETTING)
        }
    }
}
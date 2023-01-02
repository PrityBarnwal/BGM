package com.applaunch.bgm.adapter.recycler.viewholder

import com.applaunch.appbase.adapter_base.BaseViewHolder
import com.applaunch.bgm.databinding.ItemListWebinarBinding
import com.applaunch.bgm.model.dto.webinar_data.WebinarDescriptionModel
import com.applaunch.bgm.utils.Constant
import com.applaunch.bgm.viewmodel.fragment.home.WebinarViewModel

class WebinarViewHolder (
    var binding: ItemListWebinarBinding,
    var viewModel: WebinarViewModel,
) : BaseViewHolder<WebinarDescriptionModel, ItemListWebinarBinding>(binding) {
    override fun populateData(data: WebinarDescriptionModel, position: Int) {

        binding.webinarList = data
        when (data.type) {
            Constant.WebinarType.TEXT -> binding.webinarType = Constant.WebinarType.TEXT
            Constant.WebinarType.IMAGE -> binding.webinarType = Constant.WebinarType.IMAGE
            else -> binding.webinarType = Constant.WebinarType.PARAGRAPH
        }
    }
}
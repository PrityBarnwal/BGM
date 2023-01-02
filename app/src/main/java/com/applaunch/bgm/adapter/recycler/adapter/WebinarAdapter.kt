package com.applaunch.bgm.adapter.recycler.adapter

import android.view.ViewGroup
import com.applaunch.appbase.adapter_base.BaseRecyclerAdapter
import com.applaunch.bgm.R
import com.applaunch.bgm.adapter.recycler.viewholder.WebinarViewHolder
import com.applaunch.bgm.databinding.ItemListWebinarBinding
import com.applaunch.bgm.model.dto.webinar_data.WebinarDescriptionModel
import com.applaunch.bgm.viewmodel.fragment.home.WebinarViewModel

class WebinarAdapter(list: MutableList<WebinarDescriptionModel>,
                     var webinarViewModel: WebinarViewModel,
) : BaseRecyclerAdapter<WebinarDescriptionModel, WebinarViewHolder>(list) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WebinarViewHolder {
        return WebinarViewHolder(
            inflateDataBinding(R.layout.item_list_webinar, parent) as ItemListWebinarBinding,
            webinarViewModel
        )
    }
}

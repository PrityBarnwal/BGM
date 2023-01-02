package com.applaunch.bgm.adapter.recycler.viewholder

import com.applaunch.appbase.adapter_base.BaseViewHolder
import com.applaunch.bgm.databinding.CollectionListItemBinding
import com.applaunch.bgm.model.dto.collection_list.CollectListData
import com.applaunch.bgm.utils.ListenerConstant
import com.applaunch.bgm.viewmodel.fragment.home.HomeFragmentViewModel

class CollectionListViewHolder(
    var binding: CollectionListItemBinding,
    var viewModel: HomeFragmentViewModel
) : BaseViewHolder<CollectListData, CollectionListItemBinding>(binding) {

    override fun populateData(data: CollectListData, position: Int) {
        binding.data = data

        binding.root.setOnClickListener {
            viewModel.collectAdapter.onItemClick?.invoke(position,data,ListenerConstant.CollectionList.ADD_FAV)
        }
    }
}
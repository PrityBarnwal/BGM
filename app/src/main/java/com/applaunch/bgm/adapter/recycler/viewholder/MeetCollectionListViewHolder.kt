package com.applaunch.bgm.adapter.recycler.viewholder

import com.applaunch.appbase.adapter_base.BaseViewHolder
import com.applaunch.bgm.databinding.CollectionListItemBinding
import com.applaunch.bgm.model.dto.collection_list.CollectListData
import com.applaunch.bgm.utils.ListenerConstant
import com.applaunch.bgm.viewmodel.fragment.meet.MeetFragmentViewModel

class MeetCollectionListViewHolder (
    var binding: CollectionListItemBinding,
    var viewModel: MeetFragmentViewModel
) : BaseViewHolder<CollectListData, CollectionListItemBinding>(binding) {

    override fun populateData(data: CollectListData, position: Int) {
        binding.data = data

        binding.root.setOnClickListener {
            viewModel.meetCollectionAdapter.onItemClick?.invoke(position,data, ListenerConstant.CollectionList.ADD_FAV)
        }
    }
}
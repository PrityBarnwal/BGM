package com.applaunch.bgm.adapter.recycler.adapter

import android.view.ViewGroup
import com.applaunch.appbase.adapter_base.BaseRecyclerAdapter
import com.applaunch.bgm.R
import com.applaunch.bgm.adapter.recycler.viewholder.CollectionListViewHolder
import com.applaunch.bgm.databinding.CollectionListItemBinding
import com.applaunch.bgm.model.dto.collection_list.CollectListData
import com.applaunch.bgm.viewmodel.fragment.home.HomeFragmentViewModel

class CollectionListAdapter(
    list: ArrayList<CollectListData>,
    var homeViewModel: HomeFragmentViewModel
) : BaseRecyclerAdapter<CollectListData, CollectionListViewHolder>(list) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionListViewHolder {
        return CollectionListViewHolder(
            inflateDataBinding(
                R.layout.inflate_collect_list_item,
                parent
            ) as CollectionListItemBinding, homeViewModel
        )
    }
}
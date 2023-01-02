package com.applaunch.bgm.adapter.recycler.adapter

import android.view.ViewGroup
import com.applaunch.appbase.adapter_base.BaseRecyclerAdapter
import com.applaunch.bgm.R
import com.applaunch.bgm.adapter.recycler.viewholder.FavoriteListViewHolder
import com.applaunch.bgm.databinding.ItemListFavoriteInsideBinding
import com.applaunch.bgm.model.dto.favorite_data.FavoriteListDataModel
import com.applaunch.bgm.viewmodel.fragment.favorite.FavoriteListViewModel

class FavoriteListAdapter(
    list: MutableList<FavoriteListDataModel>,
    var favoriteListViewModel: FavoriteListViewModel
) : BaseRecyclerAdapter<FavoriteListDataModel, FavoriteListViewHolder>(list) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteListViewHolder {
        return FavoriteListViewHolder(
            inflateDataBinding(
                R.layout.item_list_favorite_inside,
                parent
            ) as ItemListFavoriteInsideBinding, favoriteListViewModel
        )
    }
}
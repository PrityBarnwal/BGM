package com.applaunch.bgm.adapter.recycler.adapter

import android.view.ViewGroup
import com.applaunch.appbase.adapter_base.BaseRecyclerAdapter
import com.applaunch.bgm.R
import com.applaunch.bgm.adapter.recycler.viewholder.FavoriteViewHolder
import com.applaunch.bgm.databinding.ItemListFavoritesBinding
import com.applaunch.bgm.model.dto.favorite_data.FavoriteDataModel
import com.applaunch.bgm.viewmodel.fragment.favorite.FavoriteViewModel

class FavoriteAdapter(
    var list: MutableList<FavoriteDataModel>,
    var favoriteViewModel: FavoriteViewModel
) : BaseRecyclerAdapter<FavoriteDataModel, FavoriteViewHolder>(list) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(
            inflateDataBinding(
                R.layout.item_list_favorites,
                parent
            ) as ItemListFavoritesBinding,
            favoriteViewModel,
            if (list.size == 1) list[0].coverImage else "",
            if (list.size == 2) list[1].coverImage else "",
            if (list.size == 3) list[2].coverImage else "",
        )
    }
}
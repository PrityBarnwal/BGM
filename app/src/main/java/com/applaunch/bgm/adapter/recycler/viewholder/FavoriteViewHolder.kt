package com.applaunch.bgm.adapter.recycler.viewholder

import com.applaunch.appbase.adapter_base.BaseViewHolder
import com.applaunch.appbase.utils_base.Print
import com.applaunch.bgm.R
import com.applaunch.bgm.databinding.ItemListFavoritesBinding
import com.applaunch.bgm.model.dto.favorite_data.FavoriteDataModel
import com.applaunch.bgm.utils.ListenerConstant
import com.applaunch.bgm.viewmodel.fragment.favorite.FavoriteViewModel

class FavoriteViewHolder(
    var binding: ItemListFavoritesBinding,
    var viewModel: FavoriteViewModel,
    private var image1: String? = null,
    var image2: String? = null,
    var image3: String? = null
) : BaseViewHolder<FavoriteDataModel, ItemListFavoritesBinding>(binding) {
    override fun populateData(data: FavoriteDataModel, position: Int) {

        binding.clickListener = this
        binding.position = position
        binding.favoriteList = data

        if (position == 0) {
            binding.image1 = image1
            binding.image2 = image2
            binding.image3 = image3
            binding.tvTitleFavorite.text =
                binding.tvTitleFavorite.context.getString(R.string.all_favorites)
        } else{
            binding.tvTitleFavorite.text = data.collectionName
            binding.image1 = data.coverImage
        }
    }

    fun itemClick(data: FavoriteDataModel, position: Int) {
        Print.log("data $data position $position")
        viewModel.favoriteAdapter.onItemClick?.invoke(
            position,
            data,
            ListenerConstant.CollectionList.FAV
        )
    }
}
package com.applaunch.bgm.adapter.recycler.viewholder

import android.view.View
import com.applaunch.appbase.adapter_base.BaseViewHolder
import com.applaunch.appbase.utils_base.BaseConstants
import com.applaunch.appbase.utils_base.equalIgnore
import com.applaunch.appbase.utils_base.setDrawable
import com.applaunch.appbase.utils_base.shareLink
import com.applaunch.bgm.R
import com.applaunch.bgm.databinding.ItemListFavoriteInsideBinding
import com.applaunch.bgm.model.dto.favorite_data.FavoriteListDataModel
import com.applaunch.bgm.utils.Constant
import com.applaunch.bgm.utils.ListenerConstant
import com.applaunch.bgm.viewmodel.fragment.favorite.FavoriteListViewModel

class FavoriteListViewHolder(
    var binding: ItemListFavoriteInsideBinding,
    var viewModel: FavoriteListViewModel
) : BaseViewHolder<FavoriteListDataModel, ItemListFavoriteInsideBinding>(binding) {
    override fun populateData(data: FavoriteListDataModel, position: Int) {
        binding.favoriteItem = data
        binding.clickItemListener = this
        binding.itemPosition = position
        binding.isBgSelected = data.bgSelected

        when (data.streamType) {
            Constant.StreamType.VIDEO -> binding.streamType = Constant.StreamType.VIDEO
            Constant.StreamType.WEBINAR -> binding.streamType = Constant.StreamType.WEBINAR
            else -> binding.streamType = Constant.StreamType.ARTICLE

        }
        if (data.isLike) {
            binding.tvFavoriteLike.setDrawable(
                R.drawable.ic_like_selected,
                BaseConstants.Position.START
            )
        } else {
            binding.tvFavoriteLike.setDrawable(
                R.drawable.ic_like_unselected,
                BaseConstants.Position.START
            )
        }
        binding.tvFavoriteLike.setOnClickListener {

            onItemClick(data, position, ListenerConstant.FAVORITE.LIKE)
        }
        binding.ivFavorite.setOnClickListener {
            onItemClick(data, position, ListenerConstant.FAVORITE.FAV)

        }
    }

    fun navigateFavBroadcast(view: View) {
        view.context.shareLink(data!!.webinarLink)
    }

    fun navigateFavVideo(data: FavoriteListDataModel, position: Int, streamType: String) {
        var type = ""
        if (streamType.equalIgnore(Constant.StreamType.VIDEO)) {
            type = ListenerConstant.FAVORITE.OPEN_VIDEO
        } else if (streamType.equalIgnore(Constant.StreamType.ARTICLE)) {
            type = ListenerConstant.FAVORITE.OPEN_ARTICLE
        }else if (streamType.equalIgnore(Constant.StreamType.WEBINAR)){
            type = ListenerConstant.FAVORITE.OPEN_WEBINAR
        }

        onItemClick(data, position, type)
    }

    fun onItemClick(data: FavoriteListDataModel, position: Int, streamType: String) {
        viewModel.favoriteListAdapter.onItemClick?.invoke(
            position,
            data,
            streamType
        )
    }
    fun onItemBgSelected(data: FavoriteListDataModel,position: Int){
        viewModel.favoriteListAdapter.onItemClick?.invoke(
            position,
            data,
            ListenerConstant.FAVORITE.BACKGROUND
        )
    }
}
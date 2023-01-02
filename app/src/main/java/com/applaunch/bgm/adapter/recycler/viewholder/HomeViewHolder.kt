package com.applaunch.bgm.adapter.recycler.viewholder

import android.view.View
import com.applaunch.appbase.adapter_base.BaseViewHolder
import com.applaunch.appbase.utils_base.BaseConstants
import com.applaunch.appbase.utils_base.equalIgnore
import com.applaunch.appbase.utils_base.setDrawable
import com.applaunch.appbase.utils_base.shareLink
import com.applaunch.bgm.R
import com.applaunch.bgm.databinding.ItemListHomeBinding
import com.applaunch.bgm.model.dto.home_data.HomeDataModel
import com.applaunch.bgm.utils.Constant
import com.applaunch.bgm.utils.ListenerConstant
import com.applaunch.bgm.viewmodel.fragment.home.HomeFragmentViewModel

class HomeViewHolder(
    var binding: ItemListHomeBinding,
    var viewModel: HomeFragmentViewModel,
) : BaseViewHolder<HomeDataModel, ItemListHomeBinding>(binding) {

    override fun populateData(data: HomeDataModel, position: Int) {
        binding.homeListItem = data
        binding.clickHomeListener = this
        binding.itemPositionHome = position
        binding.isBgSelected = data.bgSelected

        when (data.streamType) {
            Constant.StreamType.VIDEO -> binding.streamType = Constant.StreamType.VIDEO
            Constant.StreamType.WEBINAR -> binding.streamType = Constant.StreamType.WEBINAR
            else -> binding.streamType = Constant.StreamType.ARTICLE
        }

        if (data.isLike) {
            binding.tvHomeLike.setDrawable(
                R.drawable.ic_like_selected,
                BaseConstants.Position.START
            )
        } else {
            binding.tvHomeLike.setDrawable(
                R.drawable.ic_like_unselected,
                BaseConstants.Position.START
            )
        }

        binding.tvHomeLike.setOnClickListener {
            viewModel.homeAdapter.onItemClick?.invoke(position, data, ListenerConstant.HOME.LIKE)
        }

        binding.ivHomeFavorites.setOnClickListener {
            viewModel.homeAdapter.onItemClick?.invoke(position, data, ListenerConstant.HOME.FAV)
        }
    }

    fun navigateBroadCast(view: View) {
        view.context.shareLink(data!!.link)
    }

    fun navigateToArticleVideo(data: HomeDataModel, position: Int, streamType: String) {
        if (streamType.equalIgnore(Constant.StreamType.VIDEO)) {
            viewModel.homeAdapter.onItemClick?.invoke(
                position,
                data,
                ListenerConstant.HOME.OPEN_VIDEO
            )
        } else if (streamType.equalIgnore(Constant.StreamType.ARTICLE)) {
            viewModel.homeAdapter.onItemClick?.invoke(
                position,
                data,
                ListenerConstant.HOME.OPEN_ARTICLE
            )
        }else if (streamType.equalIgnore(Constant.StreamType.WEBINAR)){
            viewModel.homeAdapter.onItemClick?.invoke(
                position,
                data,
                ListenerConstant.HOME.OPEN_WEBINAR
            )
        }
    }

    fun onItemBgChange(data: HomeDataModel, position: Int) {
        viewModel.homeAdapter.onItemClick?.invoke(
            position,
            data,
            ListenerConstant.HOME.BACKGROUND
        )
    }
}
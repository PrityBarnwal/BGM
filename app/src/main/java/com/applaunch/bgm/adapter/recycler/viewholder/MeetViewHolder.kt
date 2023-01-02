package com.applaunch.bgm.adapter.recycler.viewholder

import android.view.View
import com.applaunch.appbase.adapter_base.BaseViewHolder
import com.applaunch.appbase.utils_base.*
import com.applaunch.bgm.R
import com.applaunch.bgm.databinding.ItemListMediathekBinding
import com.applaunch.bgm.model.dto.meet_data.MeetDataModel
import com.applaunch.bgm.utils.Constant
import com.applaunch.bgm.utils.ListenerConstant
import com.applaunch.bgm.viewmodel.fragment.meet.MeetFragmentViewModel

class MeetViewHolder(
    var binding: ItemListMediathekBinding,
    var viewModel: MeetFragmentViewModel,
) : BaseViewHolder<MeetDataModel, ItemListMediathekBinding>(binding) {

    override fun populateData(data: MeetDataModel, position: Int) {
        Print.log("data home $data")
        binding.meetList = data
        binding.clickListener = this
        binding.position = position
        binding.isBgSelected = data.bgSelected

        when (data.streamType) {
            Constant.StreamType.VIDEO -> binding.streamType = Constant.StreamType.VIDEO
            Constant.StreamType.WEBINAR -> binding.streamType = Constant.StreamType.WEBINAR
            else -> binding.streamType = Constant.StreamType.ARTICLE

        }
        if (data.isLike) {
            binding.tvMediaThekLikes.setDrawable(
                R.drawable.ic_like_selected,
                BaseConstants.Position.START
            )
        } else {
            binding.tvMediaThekLikes.setDrawable(
                R.drawable.ic_like_unselected,
                BaseConstants.Position.START
            )
        }

        binding.tvMediaThekLikes.setOnClickListener {
            viewModel.meetAdapter.onItemClick?.invoke(position, data, ListenerConstant.MEDIA.LIKE)
        }
        binding.ivfavorites.setOnClickListener {
            viewModel.meetAdapter.onItemClick?.invoke(position, data, ListenerConstant.MEDIA.FAV)
        }
    }

    fun navigateToBroadcast(view:View){
       view.context.shareLink(data!!.link)
    }

    fun navigateToArticle(data: MeetDataModel, position: Int, streamType: String) {
        Print.log("Navigate to openVideo")
        if (streamType.equalIgnore(Constant.StreamType.VIDEO)) {
            Print.log("Navigate to openVideo")
            viewModel.meetAdapter.onItemClick?.invoke(
                position,
                data,
                ListenerConstant.MEDIA.OPEN_VIDEO
            )
        } else if (streamType.equalIgnore(Constant.StreamType.ARTICLE)) {
            viewModel.meetAdapter.onItemClick?.invoke(
                position,
                data,
                ListenerConstant.MEDIA.OPEN_ARTICLE
            )
        }else if (streamType.equalIgnore(Constant.StreamType.WEBINAR)){
            viewModel.meetAdapter.onItemClick?.invoke(
                position,
                data,
                ListenerConstant.MEDIA.OPEN_WEBINAR
            )
        }
    }
    fun onBgChange(data: MeetDataModel,position: Int){
        viewModel.meetAdapter.onItemClick?.invoke(position, data, ListenerConstant.MEDIA.BACKGROUND)
    }
}
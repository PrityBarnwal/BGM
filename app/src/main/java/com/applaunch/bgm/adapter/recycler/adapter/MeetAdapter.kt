package com.applaunch.bgm.adapter.recycler.adapter

import android.view.ViewGroup
import com.applaunch.appbase.adapter_base.BaseRecyclerAdapter
import com.applaunch.bgm.R
import com.applaunch.bgm.adapter.recycler.viewholder.MeetViewHolder
import com.applaunch.bgm.databinding.ItemListMediathekBinding
import com.applaunch.bgm.model.dto.meet_data.MeetDataModel
import com.applaunch.bgm.viewmodel.fragment.meet.MeetFragmentViewModel

class MeetAdapter(list: MutableList<MeetDataModel>,
                  var meetFragmentViewModel: MeetFragmentViewModel,
                  ): BaseRecyclerAdapter<MeetDataModel, MeetViewHolder>(list) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeetViewHolder {
        return MeetViewHolder(
            inflateDataBinding(
                R.layout.item_list_mediathek,
                parent
            ) as ItemListMediathekBinding, meetFragmentViewModel
        )
    }
}
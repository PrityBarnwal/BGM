package com.applaunch.bgm.adapter.recycler.adapter

import android.view.ViewGroup
import com.applaunch.appbase.adapter_base.BaseRecyclerAdapter
import com.applaunch.bgm.R
import com.applaunch.bgm.adapter.recycler.viewholder.HomeViewHolder
import com.applaunch.bgm.databinding.ItemListHomeBinding
import com.applaunch.bgm.model.dto.home_data.HomeDataModel
import com.applaunch.bgm.viewmodel.fragment.home.HomeFragmentViewModel

class HomeAdapter(
    list: MutableList<HomeDataModel>,
    var homeFragmentViewModel: HomeFragmentViewModel
) : BaseRecyclerAdapter<HomeDataModel, HomeViewHolder>(list) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            inflateDataBinding(
                R.layout.item_list_home,
                parent
            ) as ItemListHomeBinding, homeFragmentViewModel
        )
    }
}
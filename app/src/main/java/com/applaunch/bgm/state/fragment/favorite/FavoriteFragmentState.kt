package com.applaunch.bgm.state.fragment.favorite

import com.applaunch.bgm.adapter.recycler.adapter.FavoriteAdapter
import com.applaunch.bgm.model.dto.favorite_data.FavoriteDataModel

sealed class FavoriteFragmentState {
    object Init:FavoriteFragmentState()
    data class NavigateFavoriteItemFragment(val id:String,val collectionName:String,val collectionAdapterPosition: Int):FavoriteFragmentState()
   data class NavigateFavoriteList(val collectionName:String):FavoriteFragmentState()
    data class SUCCESS(val data:ArrayList<FavoriteDataModel>): FavoriteFragmentState()
    data class ERROR(val msg:String): FavoriteFragmentState()
    data class FavoriteList(val adapter:FavoriteAdapter):FavoriteFragmentState()
}
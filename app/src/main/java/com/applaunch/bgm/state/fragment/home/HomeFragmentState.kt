package com.applaunch.bgm.state.fragment.home

import com.applaunch.appbase.model_base.BaseResponse
import com.applaunch.bgm.adapter.recycler.adapter.HomeAdapter
import com.applaunch.bgm.model.dto.collection_list.CollectListData
import com.applaunch.bgm.model.dto.home_data.HomeDataModel

sealed class HomeFragmentState {
    object Init : HomeFragmentState()
    data class NavigateVideo(val videoTitle:String,val video:String,val isFav:Boolean) : HomeFragmentState()
    data class NavigateArticle(val id:String) : HomeFragmentState()
    data class NavigateWebinar(val id:String) : HomeFragmentState()
    data class SUCCESS(val data: ArrayList<HomeDataModel>) : HomeFragmentState()
    data class ERROR(val msg: String) : HomeFragmentState()
    data class CollectAddedSuccess(val msg: String) : HomeFragmentState()
    data class CollectAddedError(val msg: String) : HomeFragmentState()
    data class AddedCollectionList(val collection: HomeDataModel,val position:Int) : HomeFragmentState()
    data class HomeList(var adapter: HomeAdapter) : HomeFragmentState()
    data class GetCollectionList(val favItemData: HomeDataModel, val homeAdapterPosition: Int) :
        HomeFragmentState()

    data class CollectionListSuccess(val data: ArrayList<CollectListData>) : HomeFragmentState()
    data class LikeDislikePost(val homeAdapterPosition: Int) : HomeFragmentState()
    data class LikeSuccess(val data: BaseResponse) : HomeFragmentState()
    data class LikeError(val msg: String) : HomeFragmentState()


    data class AddFavSuccess(val msg: String) : HomeFragmentState()
    data class RemoveFav(val favItemData: HomeDataModel, val homeAdapterPosition: Int) :
        HomeFragmentState()

    data class RemoveFavError(val msg: String) : HomeFragmentState()

    data class ItemSelected(var currentPosition: Int) :
        HomeFragmentState()
}

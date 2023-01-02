package com.applaunch.bgm.state.fragment.meet

import com.applaunch.appbase.model_base.BaseResponse
import com.applaunch.bgm.adapter.recycler.adapter.MeetAdapter
import com.applaunch.bgm.model.dto.collection_list.CollectListData
import com.applaunch.bgm.model.dto.meet_data.MeetDataModel

sealed class MeetFragmentState {
    object Init : MeetFragmentState()
    data class SUCCESS(val data: ArrayList<MeetDataModel>) : MeetFragmentState()
    data class ERROR(val msg: String) : MeetFragmentState()
    data class NavigateToVideo(val videoTitle: String, val video: String,val isFav:Boolean) : MeetFragmentState()
    data class NavigateToArticle(val id: String) : MeetFragmentState()
    data class NavigateToWebinar(val id:String) : MeetFragmentState()
    data class MeetList(var adapter: MeetAdapter) : MeetFragmentState()

    //like dislike
    data class LikeDislikePost(val meetAdapterPosition: Int) : MeetFragmentState()
    data class LikeSuccess(val data: BaseResponse) : MeetFragmentState()
    data class LikeError(val msg: String) : MeetFragmentState()

    //add Or Remove Favorite
    data class AddFavSuccess(val msg: String) : MeetFragmentState()
    data class RemoveFavError(val msg: String) : MeetFragmentState()

    //collection Add
    data class CollectAddedSuccess(val msg: String) : MeetFragmentState()
    data class CollectAddedError(val msg: String) : MeetFragmentState()

    data class CollectionListSuccess(val data: ArrayList<CollectListData>) : MeetFragmentState()
    data class GetCollectionList(val favItemData: MeetDataModel, val meetAdapterPosition: Int) :
        MeetFragmentState()

    data class RemoveFromFav(val favItemData: MeetDataModel, val meetAdapterPosition: Int) :
        MeetFragmentState()

    data class ItemSelected(var currentPosition: Int) :
        MeetFragmentState()
}
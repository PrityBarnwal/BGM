package com.applaunch.bgm.state.fragment.favorite

import com.applaunch.appbase.model_base.BaseResponse
import com.applaunch.bgm.adapter.recycler.adapter.FavoriteListAdapter
import com.applaunch.bgm.model.dto.favorite_data.FavoriteListDataModel

sealed class FavoriteListState{
    object Init:FavoriteListState()
    data class SUCCESS(val data:ArrayList<FavoriteListDataModel>): FavoriteListState()
    data class ERROR(val msg:String): FavoriteListState()
    data class FavoriteListItem(val adapter: FavoriteListAdapter):FavoriteListState()
    data class NavigateVideo(val videoTitle:String,val video:String,val isFav:Boolean): FavoriteListState()
    data class NavigateArticle(val id:String): FavoriteListState()
    data class NavigateWebinar(val id:String): FavoriteListState()
    //on Favorite Click
   data class RemoveFav(val favItemData: FavoriteListDataModel, val favoriteAdapterPosition: Int) : FavoriteListState()

    //on API Call
    data class RemoveFavSuccess(val msg: String) : FavoriteListState()
    data class RemoveFavError(val msg: String) : FavoriteListState()

    //like dislike
    data class LikeDislikePost(val favoriteAdapterPosition: Int) : FavoriteListState()
    data class LikeSuccess(val data: BaseResponse) : FavoriteListState()
    data class LikeError(val msg: String) : FavoriteListState()

    //delete Collection
    data class DeleteCollectionSuccess(val data: BaseResponse) : FavoriteListState()
    data class DeleteCollectionError(val msg: String) : FavoriteListState()

    data class AllFavSUCCESS(val data:ArrayList<FavoriteListDataModel>): FavoriteListState()
    data class AllFavERROR(val msg:String): FavoriteListState()

    data class ItemSelected(var currentPosition: Int) :
        FavoriteListState()
}

package com.applaunch.bgm.viewmodel.fragment.favorite

import android.os.Bundle
import androidx.lifecycle.viewModelScope
import com.applaunch.appbase.listner_base.BaseRepoListener
import com.applaunch.appbase.model_base.State
import com.applaunch.appbase.utils_base.Print
import com.applaunch.appbase.viewmodel_base.BaseViewModel
import com.applaunch.bgm.adapter.recycler.adapter.FavoriteListAdapter
import com.applaunch.bgm.model.dto.favorite_data.FavoriteListDataModel
import com.applaunch.bgm.model.request.favorite.FavoriteListRequest
import com.applaunch.bgm.model.request.like_dislike.LikeDislikeRequest
import com.applaunch.bgm.model.request.save_remove_fav.SaveRemoveFavRequest
import com.applaunch.bgm.network.NetworkRepository
import com.applaunch.bgm.state.fragment.favorite.FavoriteListState
import com.applaunch.bgm.utils.Constant
import com.applaunch.bgm.utils.ListenerConstant
import doNothing
import kotlinx.coroutines.launch

class FavoriteListViewModel : BaseViewModel<FavoriteListState>() {
    lateinit var favoriteListAdapter: FavoriteListAdapter
    var favoriteItemId: String = ""
    var deleteCollectionId: String = ""
    var favoriteListState: FavoriteListState = FavoriteListState.Init
        set(value) {
            field = value
            publishState(favoriteListState)
        }

    val saveRemoveFavRequest = SaveRemoveFavRequest()
    val favoriteListRequest = FavoriteListRequest()
    private var likeDislikeRequest = LikeDislikeRequest()

    override fun onInitialized(bundle: Bundle?) {
        //do letter
    }

    fun favoriteItem(baseRepoListener: BaseRepoListener) {
        viewModelScope.launch {
            NetworkRepository(baseRepoListener).favoriteItem(favoriteItemId, favoriteListRequest)
                .collect {
                    when (it) {
                        is State.Success -> {
                            favoriteListState = FavoriteListState.SUCCESS(it.data.data)
                        }
                        is State.Error -> {
                            favoriteListState = FavoriteListState.ERROR(it.message)
                        }
                        else -> {
                            doNothing()
                        }
                    }
                }
        }
    }

    fun allFavorite(baseRepoListener: BaseRepoListener) {
        viewModelScope.launch {
            NetworkRepository(baseRepoListener).allFavorite(favoriteListRequest).collect {
                when (it) {
                    is State.Success -> {
                        favoriteListState = FavoriteListState.AllFavSUCCESS(it.data.data)
                        Print.log("allSuccess ${it.data.data}")
                    }
                    is State.Error -> {
                        favoriteListState = FavoriteListState.AllFavERROR(it.message)
                    }
                    else -> {
                        doNothing()
                    }
                }
            }
        }
    }

    fun initFavoriteItemList(dataList: ArrayList<FavoriteListDataModel>) {
        favoriteListAdapter = FavoriteListAdapter(dataList, this)
        favoriteListState = FavoriteListState.FavoriteListItem(favoriteListAdapter)

        favoriteListAdapter.onItemClick = { position, item, type ->
            val favoriteItemData = item as FavoriteListDataModel
            when (type) {
                ListenerConstant.FAVORITE.FAV -> {
                    favoriteListState = FavoriteListState.RemoveFav(favoriteItemData, position)
                }

                ListenerConstant.FAVORITE.OPEN_VIDEO -> {
                    favoriteListState = FavoriteListState.NavigateVideo(
                        favoriteItemData.title,
                        favoriteItemData.video,
                        favoriteItemData.isfavorites
                    )
                }
                ListenerConstant.FAVORITE.OPEN_ARTICLE -> {
                    favoriteListState =
                        FavoriteListState.NavigateArticle(dataList[position].id.streamId)
                }
                ListenerConstant.FAVORITE.OPEN_WEBINAR -> {
                    favoriteListState =
                        FavoriteListState.NavigateWebinar(dataList[position].id.streamId)
                }
                ListenerConstant.FAVORITE.LIKE -> {
                    likeDislikeRequest.type =
                        favoriteItemData.streamType ?: Constant.StreamType.ARTICLE
                    likeDislikeRequest.id = favoriteItemData.id.streamId
                    favoriteListState = FavoriteListState.LikeDislikePost(position)
                }
                ListenerConstant.FAVORITE.BACKGROUND -> {
                    favoriteListState = FavoriteListState.ItemSelected(position)
                }
            }
        }
    }

    fun likeDislikePost(baseRepoListener: BaseRepoListener) {
        viewModelScope.launch {
            NetworkRepository(baseRepoListener).likeDislike(likeDislikeRequest).collect {
                when (it) {
                    is State.Success -> {
                        favoriteListState = FavoriteListState.LikeSuccess(it.data)
                    }
                    is State.Error -> {
                        favoriteListState = FavoriteListState.LikeError(it.message)
                    }
                    else -> {
                        doNothing()
                    }
                }
            }
        }
    }

    fun removeFav(baseRepoListener: BaseRepoListener) {
        viewModelScope.launch {
            NetworkRepository(baseRepoListener).addRemoveFav(saveRemoveFavRequest).collect {
                when (it) {
                    is State.Success -> {
                        favoriteListState = FavoriteListState.RemoveFavSuccess(it.data.message)
                    }

                    is State.Error -> {
                        favoriteListState = FavoriteListState.RemoveFavError(it.message)
                    }
                    else -> {
                        doNothing()
                    }
                }
            }
        }
    }

    fun deleteCollection(baseRepoListener: BaseRepoListener) {
        viewModelScope.launch {
            NetworkRepository(baseRepoListener).deleteCollection(deleteCollectionId).collect {
                when (it) {
                    is State.Success -> {
                        favoriteListState = FavoriteListState.DeleteCollectionSuccess(it.data)
                    }
                    is State.Error -> {
                        favoriteListState = FavoriteListState.DeleteCollectionError(it.message)
                    }
                    else -> {
                        doNothing()
                    }
                }
            }
        }
    }
    fun changeBg() {
        favoriteListAdapter.notifyDataSetChanged()
    }
}
package com.applaunch.bgm.viewmodel.fragment.home

import android.os.Bundle
import androidx.lifecycle.viewModelScope
import com.applaunch.appbase.listner_base.BaseRepoListener
import com.applaunch.appbase.model_base.State
import com.applaunch.appbase.utils_base.Print
import com.applaunch.appbase.viewmodel_base.BaseViewModel
import com.applaunch.bgm.adapter.recycler.adapter.CollectionListAdapter
import com.applaunch.bgm.adapter.recycler.adapter.HomeAdapter
import com.applaunch.bgm.model.dto.collection_list.CollectListData
import com.applaunch.bgm.model.dto.home_data.HomeDataModel
import com.applaunch.bgm.model.request.add_collection.AddCollectionRequest
import com.applaunch.bgm.model.request.like_dislike.LikeDislikeRequest
import com.applaunch.bgm.model.request.save_remove_fav.SaveRemoveFavRequest
import com.applaunch.bgm.network.NetworkRepository
import com.applaunch.bgm.state.fragment.home.HomeFragmentState
import com.applaunch.bgm.utils.Constant
import com.applaunch.bgm.utils.ListenerConstant
import doNothing
import kotlinx.coroutines.launch

class HomeFragmentViewModel : BaseViewModel<HomeFragmentState>() {
    lateinit var homeAdapter: HomeAdapter
    lateinit var collectAdapter: CollectionListAdapter

    val createCollectionRequest = AddCollectionRequest()
    private var likeDislikeRequest = LikeDislikeRequest()
    val saveRemoveFavRequest = SaveRemoveFavRequest()

    var homeFragmentState: HomeFragmentState = HomeFragmentState.Init
        set(value) {
            field = value
            publishState(homeFragmentState)
        }

    override fun onInitialized(bundle: Bundle?) {
        doNothing()
    }

    fun getHomeData(baseRepoListener: BaseRepoListener) {
        viewModelScope.launch {
            NetworkRepository(baseRepoListener).home().collect {
                when (it) {
                    is State.Success -> {
                        Print.log("home Success Response ${it.data.data}")
                        homeFragmentState = HomeFragmentState.SUCCESS(it.data.data)
                    }
                    is State.Error -> {
                        Print.log("home Error Response ${it.message}")
                        homeFragmentState = HomeFragmentState.ERROR(it.message)
                    }
                    else -> {
                        doNothing()
                    }
                }
            }
        }
    }

    fun getCollectionList(baseRepoListener: BaseRepoListener) {
        viewModelScope.launch {
            NetworkRepository(baseRepoListener).getCollectionList().collect {
                when (it) {
                    is State.Success -> {
                        Print.log("Collection List Response ${it}")
                        homeFragmentState = HomeFragmentState.CollectionListSuccess(it.data.data)
                    }

                    is State.Error -> {
                        Print.log("home Error Response ${it.message}")
                        homeFragmentState = HomeFragmentState.ERROR(it.message)
                    }
                    else -> {
                        doNothing()
                    }
                }
            }
        }
    }

    fun addCollection(baseRepoListener: BaseRepoListener) {
        viewModelScope.launch {
            NetworkRepository(baseRepoListener).createCollection(createCollectionRequest).collect {
                when (it) {
                    is State.Success -> {
                        Print.log("Add Collection Response ${it.data}")
                        homeFragmentState = HomeFragmentState.CollectAddedSuccess(it.data.message)
                    }
                    is State.Error -> {
                        Print.log("home Error Response ${it.message}")
                        homeFragmentState = HomeFragmentState.CollectAddedSuccess(it.message)
                    }
                    else -> {
                        doNothing()
                    }
                }
            }
        }
    }

    fun initHomeList(dataList: ArrayList<HomeDataModel>) {

        homeAdapter = HomeAdapter(dataList, this)
        homeFragmentState = HomeFragmentState.HomeList(homeAdapter)

        homeAdapter.onItemClick = { position, item, type ->
            val homeData = item as HomeDataModel

            when (type) {

                ListenerConstant.HOME.FAV -> {
                    if (homeData.isFavorite) {
                        homeFragmentState = HomeFragmentState.RemoveFav(homeData, position)
                    } else {
                        homeFragmentState = HomeFragmentState.GetCollectionList(homeData, position)
                    }
                }

                ListenerConstant.HOME.OPEN_VIDEO -> {
                    homeFragmentState =
                        HomeFragmentState.NavigateVideo(homeData.title, homeData.video,homeData.isFavorite)
                }
                ListenerConstant.HOME.OPEN_ARTICLE -> {
                    homeFragmentState = HomeFragmentState.NavigateArticle(dataList[position]._id)
                }
                ListenerConstant.HOME.OPEN_WEBINAR -> {
                    homeFragmentState = HomeFragmentState.NavigateWebinar(dataList[position]._id)
                }

                ListenerConstant.HOME.LIKE -> {
                    likeDislikeRequest.type = homeData.streamType ?: Constant.StreamType.ARTICLE
                    likeDislikeRequest.id = homeData._id
                    homeFragmentState = HomeFragmentState.LikeDislikePost(position)
                }
                ListenerConstant.HOME.BACKGROUND -> {
                    homeFragmentState = HomeFragmentState.ItemSelected(position)
                }
            }
        }
    }

    fun initCollectionList(list: ArrayList<CollectListData>) {
        collectAdapter = CollectionListAdapter(list, this)

    }

    fun likeDislikePost(baseRepoListener: BaseRepoListener) {
        viewModelScope.launch {
            NetworkRepository(baseRepoListener).likeDislike(likeDislikeRequest).collect {
                when (it) {
                    is State.Success -> {
                        homeFragmentState = HomeFragmentState.LikeSuccess(it.data)
                    }
                    is State.Error -> {
                        homeFragmentState = HomeFragmentState.LikeError(it.message)
                    }
                    else -> {
                        doNothing()
                    }
                }
            }
        }
    }

    fun addRemoveFav(baseRepoListener: BaseRepoListener) {
        viewModelScope.launch {
            NetworkRepository(baseRepoListener).addRemoveFav(saveRemoveFavRequest).collect {
                when (it) {
                    is State.Success -> {
                        Print.log("IsFav : AddFavSuccess")
                        homeFragmentState = HomeFragmentState.AddFavSuccess(it.data.message)
                    }

                    is State.Error -> {
                        homeFragmentState = HomeFragmentState.RemoveFavError(it.message)
                    }
                    else -> {
                        doNothing()
                    }
                }
            }
        }
    }

    fun changeBg() {
        Print.log("ChangeBg ")
        homeAdapter.notifyDataSetChanged()
    }
}
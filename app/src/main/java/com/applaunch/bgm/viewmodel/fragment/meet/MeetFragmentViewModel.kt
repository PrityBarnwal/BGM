package com.applaunch.bgm.viewmodel.fragment.meet

import android.os.Bundle
import androidx.lifecycle.viewModelScope
import com.applaunch.appbase.listner_base.BaseRepoListener
import com.applaunch.appbase.model_base.State
import com.applaunch.appbase.utils_base.Print
import com.applaunch.appbase.viewmodel_base.BaseViewModel
import com.applaunch.bgm.adapter.recycler.adapter.MeetAdapter
import com.applaunch.bgm.adapter.recycler.adapter.MeetCollectionListAdapter
import com.applaunch.bgm.model.dto.collection_list.CollectListData
import com.applaunch.bgm.model.dto.meet_data.MeetDataModel
import com.applaunch.bgm.model.request.add_collection.AddCollectionRequest
import com.applaunch.bgm.model.request.like_dislike.LikeDislikeRequest
import com.applaunch.bgm.model.request.meet.MeetRequest
import com.applaunch.bgm.model.request.save_remove_fav.SaveRemoveFavRequest
import com.applaunch.bgm.network.NetworkRepository
import com.applaunch.bgm.state.fragment.meet.MeetFragmentState
import com.applaunch.bgm.utils.Constant
import com.applaunch.bgm.utils.ListenerConstant
import doNothing
import kotlinx.coroutines.launch

class MeetFragmentViewModel : BaseViewModel<MeetFragmentState>() {
    lateinit var meetAdapter: MeetAdapter
    var meetRequest = MeetRequest()
    var skip: Int = 0
    var limit: Int = 1
    var meetFragmentState: MeetFragmentState = MeetFragmentState.Init
        set(value) {
            field = value
            publishState(meetFragmentState)
        }
    lateinit var meetCollectionAdapter: MeetCollectionListAdapter
    val createMeetCollectionRequest = AddCollectionRequest()
    val saveRemoveFavRequest = SaveRemoveFavRequest()
    private var likeDislikeRequest = LikeDislikeRequest()


    override fun onInitialized(bundle: Bundle?) {
        doNothing()
    }

        fun getMeetData(baseRepoListener: BaseRepoListener) {
        viewModelScope.launch {
            NetworkRepository(baseRepoListener).meet(skip, limit, meetRequest).collect {
                when (it) {
                    is State.Success -> {
                        meetFragmentState = MeetFragmentState.SUCCESS(it.data.data)
                    }
                    is State.Error -> {
                        meetFragmentState = MeetFragmentState.ERROR(it.message)
                    }
                }
            }
        }
    }

    fun initMeetList(dataList: ArrayList<MeetDataModel>) {
        Print.log("initMeetList $dataList")
        meetAdapter = MeetAdapter(dataList, this)
        meetFragmentState = MeetFragmentState.MeetList(meetAdapter)

        meetAdapter.onItemClick = { position, item, type ->
            val meetData = item as MeetDataModel

            when (type) {
                ListenerConstant.MEDIA.FAV -> {
                    if (meetData.isFavorite) {
                        meetFragmentState = MeetFragmentState.RemoveFromFav(meetData, position)
                    } else {
                        meetFragmentState = MeetFragmentState.GetCollectionList(meetData, position)
                    }

                }
                ListenerConstant.MEDIA.OPEN_VIDEO -> {
                    meetFragmentState = MeetFragmentState.NavigateToVideo(
                        meetData.title,
                        meetData.video,
                        meetData.isFavorite
                    )
                }
                ListenerConstant.MEDIA.OPEN_ARTICLE -> {
                    meetFragmentState = MeetFragmentState.NavigateToArticle(dataList[position]._id)
                }
                ListenerConstant.MEDIA.OPEN_WEBINAR -> {
                    meetFragmentState = MeetFragmentState.NavigateToWebinar(dataList[position]._id)
                }
                ListenerConstant.MEDIA.LIKE -> {
                    likeDislikeRequest.type = meetData.streamType ?: Constant.StreamType.ARTICLE
                    likeDislikeRequest.id = meetData._id
                    meetFragmentState = MeetFragmentState.LikeDislikePost(position)
                }
                ListenerConstant.MEDIA.BACKGROUND -> {
                    meetFragmentState = MeetFragmentState.ItemSelected(position)
                }
            }
        }
    }

    fun likeDislike(baseRepoListener: BaseRepoListener) {
        viewModelScope.launch {
            NetworkRepository(baseRepoListener).likeDislike(likeDislikeRequest).collect {
                when (it) {
                    is State.Success -> {
                        meetFragmentState = MeetFragmentState.LikeSuccess(it.data)
                    }
                    is State.Error -> {
                        meetFragmentState = MeetFragmentState.LikeError(it.message)
                    }
                }
            }
        }
    }

    fun initCollectionList(list: ArrayList<CollectListData>) {
        meetCollectionAdapter = MeetCollectionListAdapter(list, this)

    }

    fun addRemoveFav(baseRepoListener: BaseRepoListener) {
        viewModelScope.launch {
            NetworkRepository(baseRepoListener).addRemoveFav(saveRemoveFavRequest).collect {
                when (it) {
                    is State.Success -> {
                        meetFragmentState = MeetFragmentState.AddFavSuccess(it.data.message)
                    }

                    is State.Error -> {
                        meetFragmentState = MeetFragmentState.RemoveFavError(it.message)
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
            NetworkRepository(baseRepoListener).createCollection(createMeetCollectionRequest)
                .collect {
                    when (it) {
                        is State.Success -> {
                            meetFragmentState =
                                MeetFragmentState.CollectAddedSuccess(it.data.message)
                        }
                        is State.Error -> {
                            meetFragmentState = MeetFragmentState.CollectAddedSuccess(it.message)
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
                        meetFragmentState = MeetFragmentState.CollectionListSuccess(it.data.data)
                    }

                    is State.Error -> {
                        meetFragmentState = MeetFragmentState.ERROR(it.message)
                    }
                    else -> {
                        doNothing()
                    }
                }
            }
        }
    }

    fun changeBg() {
        meetAdapter.notifyDataSetChanged()
    }


}
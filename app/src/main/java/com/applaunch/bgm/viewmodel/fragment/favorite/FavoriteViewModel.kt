package com.applaunch.bgm.viewmodel.fragment.favorite

import android.os.Bundle
import androidx.lifecycle.viewModelScope
import com.applaunch.appbase.listner_base.BaseRepoListener
import com.applaunch.appbase.model_base.State
import com.applaunch.appbase.utils_base.Print
import com.applaunch.appbase.viewmodel_base.BaseViewModel
import com.applaunch.bgm.adapter.recycler.adapter.FavoriteAdapter
import com.applaunch.bgm.model.dto.favorite_data.FavoriteDataModel
import com.applaunch.bgm.model.request.favorite.FavoriteRequest
import com.applaunch.bgm.network.NetworkRepository
import com.applaunch.bgm.state.fragment.favorite.FavoriteFragmentState
import com.applaunch.bgm.utils.ListenerConstant
import doNothing
import kotlinx.coroutines.launch

class FavoriteViewModel : BaseViewModel<FavoriteFragmentState>() {
    val favoriteRequest= FavoriteRequest()
    lateinit var favoriteAdapter: FavoriteAdapter
    var favoriteFragmentState: FavoriteFragmentState =FavoriteFragmentState.Init
        set(value) {
            field = value
            publishState(favoriteFragmentState)
        }
    override fun onInitialized(bundle: Bundle?) {
        //do letter
    }
    fun favorite(baseRepoListener: BaseRepoListener){
        viewModelScope.launch {
            NetworkRepository(baseRepoListener).favorite(favoriteRequest).collect{
                when(it){
                    is State.Success ->{
                        favoriteFragmentState = FavoriteFragmentState.SUCCESS(it.data.data)
                    }
                    is State.Error ->{
                        favoriteFragmentState = FavoriteFragmentState.ERROR(it.message)
                    }
                    else ->{
                        doNothing()
                    }
                }
            }
        }
    }
    fun initFavoriteList(dataList:ArrayList<FavoriteDataModel> ){
        Print.log("dataList $dataList")
        favoriteAdapter = FavoriteAdapter(dataList,this)
        favoriteFragmentState = FavoriteFragmentState.FavoriteList(favoriteAdapter)

        favoriteAdapter.onItemClick = { position, item, type ->
            val collectionData = item as FavoriteDataModel
            when (type) {
                ListenerConstant.CollectionList.FAV -> {
                    Print.log("positon $position")
                    if (position==0){
                        favoriteFragmentState = FavoriteFragmentState.NavigateFavoriteList("All favorites")
                    }else{
                        favoriteFragmentState = FavoriteFragmentState.NavigateFavoriteItemFragment(
                            collectionData._id,
                            collectionData.collectionName,position
                        )
                    }

                }
            }
        }
    }
}
package com.applaunch.bgm.views.fragment.favorite

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.applaunch.appbase.utils_base.getValue
import com.applaunch.appbase.utils_base.navigateTo
import com.applaunch.appbase.utils_base.onBackPressed
import com.applaunch.appbase.view_base.BaseFragment
import com.applaunch.bgm.R
import com.applaunch.bgm.databinding.FragmentFavoritesBinding
import com.applaunch.bgm.model.dto.favorite_data.FavoriteDataModel
import com.applaunch.bgm.state.fragment.favorite.FavoriteFragmentState
import com.applaunch.bgm.viewmodel.fragment.favorite.FavoriteViewModel

class FavoriteFragment: BaseFragment<FavoriteViewModel, FragmentFavoritesBinding>() {
    override val layoutId: Int = R.layout.fragment_favorites
    override val mViewModel: FavoriteViewModel by viewModels()

    lateinit var collectionList:ArrayList<FavoriteDataModel>
    override fun onFragmentCreated() {

        mViewModel.favorite(iBaseRepoListener)
        mViewBinding.clickListener = this
        mViewBinding.etFavoritesSearch.setOnEditorActionListener(object :
            TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    mViewBinding.crossIcon = true
                    mViewModel.favoriteRequest.collectionName = mViewBinding.etFavoritesSearch.getValue()
                    mViewModel.favorite(iBaseRepoListener)
                    return true
                }
                return false
            }
        })
    }
    override fun subscribeObservers() {
        mViewModel.stateObserver.observe(this){
            when(it){
                is FavoriteFragmentState.SUCCESS-> {
                    setCollectionData(it.data)
                }
                is FavoriteFragmentState.ERROR->{
                    it.msg
                }
                is FavoriteFragmentState.FavoriteList -> mViewBinding.recFavorite.adapter = it.adapter

                is FavoriteFragmentState.NavigateFavoriteItemFragment -> {
                    val bundle= Bundle()
                    bundle.putString("favoriteId",it.id)
                    bundle.putString("collectionName",it.collectionName)
                    navigateTo(R.id.favoriteListFragment,bundle)
                }
                is FavoriteFragmentState.NavigateFavoriteList -> {
                    val bundle= Bundle()
                    bundle.putString("collectionName",it.collectionName)
                    navigateTo(R.id.favoriteListFragment,bundle)
                }
            }
        }
    }
    fun clearData(view: View){
        mViewModel.favoriteRequest.collectionName =""
        mViewBinding.etFavoritesSearch.setText("")
        mViewBinding.crossIcon = false
        mViewModel.favorite(iBaseRepoListener)
    }

    private fun setCollectionData(data: ArrayList<FavoriteDataModel>){
        if (data.isNotEmpty()){
            collectionList = data
            mViewModel.initFavoriteList(collectionList)
            mViewBinding.dataNotFound = false
        }else{
            mViewBinding.dataNotFound = true
        }
    }
}
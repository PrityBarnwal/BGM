package com.applaunch.bgm.views.fragment.favorite

import android.app.Dialog
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.applaunch.appbase.utils_base.*
import com.applaunch.appbase.view_base.BaseFragment
import com.applaunch.bgm.R
import com.applaunch.bgm.databinding.FavoriteEditBinding
import com.applaunch.bgm.databinding.FragmentFavoriteListBinding
import com.applaunch.bgm.databinding.LayoutDialogFavoriteBinding
import com.applaunch.bgm.model.dto.favorite_data.FavoriteListDataModel
import com.applaunch.bgm.state.fragment.favorite.FavoriteListState
import com.applaunch.bgm.utils.Constant
import com.applaunch.bgm.viewmodel.fragment.favorite.FavoriteListViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog

class FavoriteListFragment : BaseFragment<FavoriteListViewModel, FragmentFavoriteListBinding>() {
    lateinit var removeFavDialog: Pair<View, Dialog>
    lateinit var deleteCollectionDialog: Pair<View, Dialog>
    lateinit var favoriteOption: Pair<View, BottomSheetDialog>
    override val layoutId: Int = R.layout.fragment_favorite_list
    override val mViewModel: FavoriteListViewModel by viewModels()

    private var favoriteId = ""
    private var collectionName = ""
    lateinit var streamType: String
    lateinit var postId: String
    lateinit var collectionID: String

    var favoriteAdapterPosition: Int = 0
    lateinit var favoriteList: ArrayList<FavoriteListDataModel>

    override fun onFragmentCreated() {
        mViewModel.favoriteItem(iBaseRepoListener)

        favoriteId = arguments?.getString("favoriteId").toString()
        collectionName = arguments?.getString("collectionName").toString()
        Print.log("collectionName $collectionName")

        mViewBinding.tvTitle.text = collectionName
        mViewModel.favoriteItemId = favoriteId

        if (collectionName == "All favorites") {
            mViewModel.allFavorite(iBaseRepoListener)
        }
        mViewBinding.clickListener = this
        mViewBinding.ivFavoriteBack.setOnClickListener {
            onManualBackPressed()
        }
        mViewBinding.etSearch.setOnEditorActionListener(object :
            TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    mViewBinding.crossIcon = true
                    mViewModel.favoriteListRequest.keyword = mViewBinding.etSearch.getValue()
                    mViewModel.favoriteItem(iBaseRepoListener)
                    mViewModel.allFavorite(iBaseRepoListener)
                    return true
                }
                return false
            }
        })
    }

    override fun subscribeObservers() {
        mViewModel.stateObserver.observe(this) {
            when (it) {
                is FavoriteListState.SUCCESS -> {
                    setFavoriteListData(it.data)
                }
                is FavoriteListState.ERROR -> {
                    it.msg
                }
                is FavoriteListState.AllFavSUCCESS -> {
                    mViewModel.initFavoriteItemList(it.data)
                    Print.log("favoriteData ${it.data}")
                }
                is FavoriteListState.AllFavERROR -> {
                    it.msg
                }
                is FavoriteListState.FavoriteListItem -> {
                    mViewBinding.recFavoriteItem.adapter =
                        it.adapter
                }
                is FavoriteListState.NavigateVideo -> {
                    val bundle = Bundle()
                    bundle.putString("title", it.videoTitle)
                    bundle.putString("videoUrl", it.video)
                    bundle.putBoolean("isFav", it.isFav)
                    navigateTo(R.id.videoFullScreenFragment, bundle)
                }
                is FavoriteListState.NavigateArticle -> {
                    val bundle = Bundle()
                    bundle.putString("article", it.id)
                    navigateTo(R.id.articleFragment, bundle)
                }
                is FavoriteListState.NavigateWebinar -> {
                    val bundle = Bundle()
                    bundle.putString("webinar", it.id)
                    navigateTo(R.id.webinarFragment, bundle)
                }
                is FavoriteListState.LikeDislikePost -> {
                    favoriteAdapterPosition = it.favoriteAdapterPosition
                    mViewModel.likeDislikePost(iBaseRepoListener)
                }
                is FavoriteListState.LikeSuccess -> {
                    if (favoriteList[favoriteAdapterPosition].isLike) {
                        favoriteList[favoriteAdapterPosition].countLikes -= 1
                    } else {
                        favoriteList[favoriteAdapterPosition].countLikes += 1
                    }
                    favoriteList[favoriteAdapterPosition].isLike =
                        !favoriteList[favoriteAdapterPosition].isLike
                    mViewModel.favoriteListAdapter.notifyItemChanged(favoriteAdapterPosition)
                    toastMessage(it.data.message)
                }
                is FavoriteListState.LikeError -> {
                    toastMessage(it.msg)
                }
                is FavoriteListState.RemoveFav -> {
                    openDialog()
                    postId = it.favItemData.id.streamId
                    streamType = it.favItemData.streamType ?: Constant.StreamType.ARTICLE
                    collectionID = it.favItemData.id.collectionId
                }
                is FavoriteListState.RemoveFavSuccess -> {
                    if (this::removeFavDialog.isInitialized) removeFavDialog.second.dismiss()
                    toastMessage(it.msg)
                }

                is FavoriteListState.DeleteCollectionSuccess -> {
                    if (this::deleteCollectionDialog.isInitialized) deleteCollectionDialog.second.dismiss()
                    navigateTo(R.id.navigation_favorites)
                    toastMessage(it.data.message)
                }
                is FavoriteListState.DeleteCollectionError -> {
                    if (this::deleteCollectionDialog.isInitialized) deleteCollectionDialog.second.dismiss()
                    toastMessage(it.msg)
                }
                is FavoriteListState.ItemSelected -> {
                    favoriteList.forEachIndexed { index, _ ->
                        favoriteList[index].bgSelected = index == it.currentPosition
                    }
                    mViewModel.changeBg()
                }
            }
        }
    }

    private fun openDialog() {
        val binding = LayoutDialogFavoriteBinding.inflate(layoutInflater)
        removeFavDialog = CustomDialog(requireContext()).createCustomDialog(binding)
        binding.tvNo.setOnClickListener {
            removeFavDialog.second.dismiss()
        }
        binding.tvDialogContent.text =
            getString(R.string.are_you_sure_you_want_to_remove_it_from_favorites)
        binding.tvYes.setOnClickListener {
            mViewModel.saveRemoveFavRequest.type = streamType
            mViewModel.saveRemoveFavRequest.id = postId
            mViewModel.saveRemoveFavRequest.collectionId = collectionID
            mViewModel.removeFav(iBaseRepoListener)
        }
    }

    fun multipleClick(view: View) {
        initFavoriteOption()
    }

    fun clearData(view: View) {
        mViewModel.favoriteListRequest.keyword = ""
        mViewBinding.etSearch.setText("")
        mViewBinding.crossIcon = false
        mViewModel.favoriteItem(iBaseRepoListener)
    }

    private fun setFavoriteListData(data: ArrayList<FavoriteListDataModel>) {
        if (data.isNotEmpty()) {
            favoriteList = data
            mViewModel.initFavoriteItemList(favoriteList)
            mViewBinding.dataNotFound = false
        } else {
            mViewBinding.dataNotFound = true
        }
    }

    private fun initFavoriteOption() {
        val binding = FavoriteEditBinding.inflate(layoutInflater)

        favoriteOption = CustomDialog(requireContext()).createBottomSheet(binding)

        binding.tvEditCollection.setOnClickListener {
            favoriteOption.second.dismiss()
            val bundle = Bundle()
            bundle.putString("edit", favoriteId)
            bundle.putString("collectionName", collectionName)
            navigateTo(R.id.fragmentEdit, bundle)

        }
        binding.tvDeleteCollection.setOnClickListener {
            favoriteOption.second.dismiss()
            openDeleteDialog()
        }
    }

    private fun openDeleteDialog() {
        val binding = LayoutDialogFavoriteBinding.inflate(layoutInflater)
        deleteCollectionDialog = CustomDialog(requireContext()).createCustomDialog(binding)
        binding.tvNo.setOnClickListener {
            deleteCollectionDialog.second.dismiss()
        }
        binding.tvDialogContent.text =
            getString(R.string.are_you_sure_you_want_to_delete_the_collection)
        binding.tvYes.setOnClickListener {
            mViewModel.deleteCollectionId = favoriteId
            mViewModel.deleteCollection(iBaseRepoListener)
        }
    }
}
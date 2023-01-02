package com.applaunch.bgm.views.fragment.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.applaunch.appbase.utils_base.*
import com.applaunch.appbase.view_base.BaseFragment
import com.applaunch.bgm.R
import com.applaunch.bgm.databinding.AddCollectionBinding
import com.applaunch.bgm.databinding.CollectionBottomBinding
import com.applaunch.bgm.databinding.FragmentHomeBinding
import com.applaunch.bgm.model.dto.collection_list.CollectListData
import com.applaunch.bgm.model.dto.home_data.HomeDataModel
import com.applaunch.bgm.state.fragment.home.HomeFragmentState
import com.applaunch.bgm.utils.Constant
import com.applaunch.bgm.utils.session.SessionConstant
import com.applaunch.bgm.viewmodel.fragment.home.HomeFragmentViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import doNothing
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment<HomeFragmentViewModel, FragmentHomeBinding>() {
    lateinit var addCollectionBottom: Pair<View, BottomSheetDialog>
    lateinit var collectBottomSheet: Pair<View, BottomSheetDialog>
    lateinit var postId: String
    lateinit var streamType: String
    private var collectionID: String = ""
    private lateinit var currentImgUrl: String
    lateinit var homeList: ArrayList<HomeDataModel>
    var homeAdapterPosition: Int = 0
    override val mViewModel: HomeFragmentViewModel by viewModels()
    override val layoutId: Int = R.layout.fragment_home

    override fun onFragmentCreated() {

        mViewModel.getHomeData(iBaseRepoListener)

        lifecycleScope.launch(Dispatchers.IO) {
            val companyLogo = async {
                sessionManager.getPreference(SessionConstant.COMPANY_LOGO, "").first()
            }
            mViewBinding.logo = companyLogo.await()
        }
    }

    override fun subscribeObservers() {
        mViewModel.stateObserver.observe(this) {
            when (it) {
                is HomeFragmentState.SUCCESS -> {
                    homeList = it.data
                    mViewModel.initHomeList(homeList)

                }
                is HomeFragmentState.ERROR -> {
                    it.msg
                }
                is HomeFragmentState.HomeList -> mViewBinding.recHome.adapter = it.adapter
                is HomeFragmentState.NavigateVideo -> {
                    val bundle = Bundle()
                    bundle.putString("title", it.videoTitle)
                    bundle.putString("videoUrl", it.video)
                    bundle.putBoolean("isFav", it.isFav)
                    navigateTo(R.id.videoFullScreenFragment, bundle)
                }

                is HomeFragmentState.NavigateArticle -> {
                    val bundle = Bundle()
                    bundle.putString("article", it.id)
                    navigateTo(R.id.articleFragment, bundle)
                }

                is HomeFragmentState.NavigateWebinar -> {
                    val bundle = Bundle()
                    bundle.putString("webinar", it.id)
                    navigateTo(R.id.webinarFragment, bundle)
                }
                is HomeFragmentState.GetCollectionList -> {
                    postId = it.favItemData._id
                    streamType = it.favItemData.streamType ?: Constant.StreamType.ARTICLE
                    currentImgUrl = it.favItemData.image
                    homeAdapterPosition = it.homeAdapterPosition
                    mViewModel.getCollectionList(iBaseRepoListener)

                }
                is HomeFragmentState.ItemSelected -> {
                    homeList.forEachIndexed { index, _ ->
                        homeList[index].bgSelected = index == it.currentPosition
                    }
                    mViewModel.changeBg()
                }

                is HomeFragmentState.CollectionListSuccess -> {
                    initCollectionBottom(it.data)
                }
                is HomeFragmentState.CollectAddedSuccess -> {
                    toastMessage(it.msg)
                    addCollectionBottom.second.dismiss()
                }
                is HomeFragmentState.CollectAddedError -> {
                    toastMessage(it.msg)
                }
                is HomeFragmentState.LikeDislikePost -> {
                    homeAdapterPosition = it.homeAdapterPosition
                    mViewModel.likeDislikePost(iBaseRepoListener)
                }
                is HomeFragmentState.LikeSuccess -> {
                    if (homeList[homeAdapterPosition].isLike) {
                        homeList[homeAdapterPosition].totalLikes -= 1
                    } else {
                        homeList[homeAdapterPosition].totalLikes += 1
                    }
                    homeList[homeAdapterPosition].isLike =
                        !homeList[homeAdapterPosition].isLike
                    mViewModel.homeAdapter.notifyItemChanged(homeAdapterPosition)
                    toastMessage(it.data.message)
                }
                is HomeFragmentState.LikeError -> {
                    toastMessage(it.msg)
                }

                is HomeFragmentState.AddFavSuccess -> {
                    if (this::collectBottomSheet.isInitialized) collectBottomSheet.second.dismiss()

                    Print.log("IsFav : ${homeList[homeAdapterPosition].isFavorite}")

                    homeList[homeAdapterPosition].isFavorite =
                        !homeList[homeAdapterPosition].isFavorite

                    mViewModel.homeAdapter.notifyItemChanged(homeAdapterPosition)
                    toastMessage(it.msg)
                }

                is HomeFragmentState.RemoveFavError -> {
                    if (this::collectBottomSheet.isInitialized) collectBottomSheet.second.dismiss()

                    toastMessage(it.msg)
                }

                is HomeFragmentState.RemoveFav -> {
                    postId = it.favItemData._id
                    streamType = it.favItemData.streamType ?: Constant.StreamType.ARTICLE
                    it.favItemData.collectionId?.let {
                        collectionID = it
                    }
                    homeAdapterPosition = it.homeAdapterPosition
                    mViewModel.saveRemoveFavRequest.type = streamType
                    mViewModel.saveRemoveFavRequest.id = postId
                    mViewModel.saveRemoveFavRequest.collectionId = collectionID
                    mViewModel.addRemoveFav(iBaseRepoListener)
                }

                else -> {
                    doNothing()
                }
            }
        }
    }

    private fun initCollectionBottom(list: ArrayList<CollectListData>) {
        val binding = CollectionBottomBinding.inflate(layoutInflater)
        collectBottomSheet = CustomDialog(requireContext()).createBottomSheet(binding)

        binding.apply {
            ivPlus.setOnClickListener {
                collectBottomSheet.second.dismiss()
                intiAddCollection()
            }
        }
        mViewModel.initCollectionList(list)
        val collectionAdapter = mViewModel.collectAdapter
        binding.recSaveFavorite.adapter = collectionAdapter

        collectionAdapter.onItemClick = { _, item, _ ->
            val data = item as CollectListData
            mViewModel.saveRemoveFavRequest.collectionId = data._id
            mViewModel.saveRemoveFavRequest.type = streamType
            mViewModel.saveRemoveFavRequest.id = postId
            mViewModel.addRemoveFav(iBaseRepoListener)
        }
    }

    private fun intiAddCollection() {
        val binding = AddCollectionBinding.inflate(layoutInflater)

        addCollectionBottom = CustomDialog(requireContext()).createBottomSheet(binding)

        binding.imgUrl = currentImgUrl
        binding.collectionData = mViewModel.createCollectionRequest

        binding.ivCollectionBack.setOnClickListener {
            addCollectionBottom.second.dismiss()
        }

        binding.tvSave.setOnClickListener {
            if (binding.etCollection.getValue().isNotEmpty()) {
                mViewModel.createCollectionRequest.type = streamType
                mViewModel.createCollectionRequest.id = postId
                mViewModel.addCollection(iBaseRepoListener)
            } else {
                toastMessage(getString(R.string.enterCollection))
            }
        }
    }
}

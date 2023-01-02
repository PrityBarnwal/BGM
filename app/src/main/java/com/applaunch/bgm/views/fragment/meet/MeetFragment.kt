package com.applaunch.bgm.views.fragment.meet

import android.os.Bundle
import android.text.InputType
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.applaunch.appbase.utils_base.*
import com.applaunch.appbase.view_base.BaseFragment
import com.applaunch.bgm.R
import com.applaunch.bgm.databinding.AddCollectionBinding
import com.applaunch.bgm.databinding.CollectionBottomBinding
import com.applaunch.bgm.databinding.FragmentMediaThekBinding
import com.applaunch.bgm.model.dto.collection_list.CollectListData
import com.applaunch.bgm.model.dto.meet_data.MeetDataModel
import com.applaunch.bgm.state.fragment.meet.MeetFragmentState
import com.applaunch.bgm.utils.Constant
import com.applaunch.bgm.viewmodel.fragment.meet.MeetFragmentViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import doNothing
import timber.log.Timber

class MeetFragment : BaseFragment<MeetFragmentViewModel, FragmentMediaThekBinding>() {

    override val layoutId: Int = R.layout.fragment_media_thek
    private var meetAdapterPosition: Int = 0
    lateinit var meetList: ArrayList<MeetDataModel>
    lateinit var addCollectionBottom: Pair<View, BottomSheetDialog>
    lateinit var collectBottomSheet: Pair<View, BottomSheetDialog>
    lateinit var postId: String
    lateinit var streamType: String
    lateinit var collectionID: String
    private lateinit var currentImgUrl: String
    private var visibleItemCount = 0
    private var totalItemCount = 0
    private var pastVisibleItems = 0
    private var totalAdapterCount = 0
    var manager: LinearLayoutManager? = null

    override val mViewModel: MeetFragmentViewModel by viewModels()
    override fun onFragmentCreated() {


         mViewModel.getMeetData(iBaseRepoListener)
        setAllMedia()
        mViewBinding.apply {
            clickListener = this@MeetFragment
            crossIcon = false

        }
        mViewBinding.etMediaSearch.setOnEditorActionListener(object :
            TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    mViewBinding.crossIcon = true
                    mViewModel.meetRequest.title = mViewBinding.etMediaSearch.getValue()
                    mViewModel.getMeetData(iBaseRepoListener)
                    return true
                }
                return false
            }
        })


        manager = LinearLayoutManager(
            requireActivity(),
            LinearLayoutManager.VERTICAL,
            false
        )

        mViewBinding.recMediaThek.layoutManager = manager
        mViewBinding.recMediaThek.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    visibleItemCount = manager!!.childCount
                    totalItemCount = manager!!.itemCount
                    pastVisibleItems = manager!!.findFirstVisibleItemPosition()
                    Timber.d("onScrolledView2 :$visibleItemCount ,$totalItemCount,$pastVisibleItems")
                    if (visibleItemCount + pastVisibleItems >= totalItemCount && totalAdapterCount != mViewModel.meetAdapter.itemCount) {
                        mViewModel.limit ++
                        Print.log("limitViewModel ${mViewModel.limit}")
                        mViewModel.getMeetData(iBaseRepoListener)
                    }
                }
            }
        })
    }

    override fun subscribeObservers() {
        mViewModel.stateObserver.observe(this) {
            when (it) {
                is MeetFragmentState.SUCCESS -> {
                    setData(it.data)
                }
                is MeetFragmentState.GetCollectionList -> {
                    postId = it.favItemData._id
                    streamType = it.favItemData.streamType ?: Constant.StreamType.ARTICLE
                    currentImgUrl = it.favItemData.image
                    meetAdapterPosition = it.meetAdapterPosition
                    mViewModel.getCollectionList(iBaseRepoListener)

                }
                is MeetFragmentState.CollectionListSuccess -> {
                    initMeetCollectionBottom(it.data)
                }
                is MeetFragmentState.CollectAddedSuccess -> {
                    toastMessage(it.msg)
                    addCollectionBottom.second.dismiss()
                }
                is MeetFragmentState.CollectAddedError -> {
                    toastMessage(it.msg)
                }
                is MeetFragmentState.AddFavSuccess -> {
                    if (this::collectBottomSheet.isInitialized) collectBottomSheet.second.dismiss()

                    meetList[meetAdapterPosition].isFavorite =
                        !meetList[meetAdapterPosition].isFavorite

                    mViewModel.meetAdapter.notifyItemChanged(meetAdapterPosition)
                    toastMessage(it.msg)
                }
                is MeetFragmentState.RemoveFavError -> {
                    if (this::collectBottomSheet.isInitialized) collectBottomSheet.second.dismiss()

                    toastMessage(it.msg)
                }

                is MeetFragmentState.RemoveFromFav -> {
                    postId = it.favItemData._id
                    streamType = it.favItemData.streamType ?: Constant.StreamType.ARTICLE
                    collectionID = it.favItemData.collectionId
                    mViewModel.saveRemoveFavRequest.type = streamType
                    mViewModel.saveRemoveFavRequest.id = postId
                    mViewModel.saveRemoveFavRequest.collectionId = collectionID

                    mViewModel.addRemoveFav(iBaseRepoListener)
                }
                is MeetFragmentState.MeetList -> mViewBinding.recMediaThek.adapter = it.adapter

                is MeetFragmentState.NavigateToVideo -> {
                    val bundle=Bundle()
                    bundle.putString("title",it.videoTitle)
                    bundle.putString("videoUrl",it.video)
                    bundle.putBoolean("isFav", it.isFav)
                    navigateTo(R.id.videoFullScreenFragment,bundle)
                }

                is MeetFragmentState.NavigateToArticle -> {
                    val bundle = Bundle()
                    bundle.putString("article", it.id)
                    navigateTo(R.id.articleFragment, bundle)
                }
                is MeetFragmentState.NavigateToWebinar -> {
                    val bundle = Bundle()
                    bundle.putString("webinar", it.id)
                    navigateTo(R.id.webinarFragment, bundle)
                }
                is MeetFragmentState.LikeDislikePost -> {
                    meetAdapterPosition = it.meetAdapterPosition
                    mViewModel.likeDislike(iBaseRepoListener)
                }
                is MeetFragmentState.LikeSuccess -> {
                    if (meetList[meetAdapterPosition].isLike) {
                        meetList[meetAdapterPosition].totalLikes -= 1
                    } else {
                        meetList[meetAdapterPosition].totalLikes += 1
                    }
                    meetList[meetAdapterPosition].isLike =
                        !meetList[meetAdapterPosition].isLike
                    mViewModel.meetAdapter.notifyItemChanged(meetAdapterPosition)
                    toastMessage(it.data.message)
                }
                is MeetFragmentState.LikeError -> {
                    toastMessage(it.msg)
                }
                is MeetFragmentState.ItemSelected ->{
                    meetList.forEachIndexed { index, _ ->
                        meetList[index].bgSelected = index == it.currentPosition
                    }
                    mViewModel.changeBg()
                }

                else -> {
                    doNothing()
                }
            }
        }
    }

    private fun setData(data: ArrayList<MeetDataModel>) {
        if (data.isNotEmpty()){
            meetList = data
            mViewModel.initMeetList(meetList)
            mViewBinding.dataNotFound = false
        }else{
            mViewBinding.dataNotFound = true
        }
    }

    fun allMedia(view: View) {
        mViewBinding.atMedia.setBackgroundResource(R.drawable.bg_spinner_all_media)
    }

    fun clearData(view: View) {
        mViewModel.meetRequest.title = ""
        mViewBinding.etMediaSearch.setText("")
        mViewBinding.crossIcon = false
        mViewModel.getMeetData(iBaseRepoListener)
    }

    private fun setAllMedia() {
        mViewBinding.apply {
            atMedia.inputType = InputType.TYPE_NULL
            val allMediaItem = resources.getStringArray(R.array.all_Media)
            val allMediaAdapter =
                ArrayAdapter(
                    requireContext(),
                    R.layout.item_spinner,
                    allMediaItem
                )
            atMedia.setAdapter(allMediaAdapter)
            tiAllMedia.hint = ""
            atMedia.setOnItemClickListener { _, _, position, _ ->
                atMedia.setBackgroundResource(R.drawable.bg_all_article)
                allMediaAdapter.getItem(position)
                mViewModel.meetRequest.type = allMediaItem[position].toString()
                mViewModel.getMeetData(iBaseRepoListener)
            }
        }
    }


    private fun initMeetCollectionBottom(list: ArrayList<CollectListData>) {
        val binding = CollectionBottomBinding.inflate(layoutInflater)
        collectBottomSheet = CustomDialog(requireContext()).createBottomSheet(binding)

        binding.apply {
            ivPlus.setOnClickListener {
                collectBottomSheet.second.dismiss()
                initAddCollection()
            }
        }
        mViewModel.initCollectionList(list)
        val collectionAdapter = mViewModel.meetCollectionAdapter
        binding.recSaveFavorite.adapter = collectionAdapter

        collectionAdapter.onItemClick = { _, item, _ ->
            val data = item as CollectListData
            mViewModel.saveRemoveFavRequest.collectionId = data._id
            mViewModel.saveRemoveFavRequest.type = streamType
            mViewModel.saveRemoveFavRequest.id = postId
            mViewModel.addRemoveFav(iBaseRepoListener)

        }
    }

    private fun initAddCollection() {
        val binding = AddCollectionBinding.inflate(layoutInflater)

        addCollectionBottom = CustomDialog(requireContext()).createBottomSheet(binding)

        binding.imgUrl = currentImgUrl
        binding.collectionData = mViewModel.createMeetCollectionRequest

        binding.ivCollectionBack.setOnClickListener {
            addCollectionBottom.second.dismiss()
        }

        binding.tvSave.setOnClickListener {
            if (binding.etCollection.getValue().isNotEmpty()) {
                mViewModel.createMeetCollectionRequest.type = streamType
                mViewModel.createMeetCollectionRequest.id = postId
                mViewModel.addCollection(iBaseRepoListener)
            } else {
                toastMessage(getString(R.string.enterCollection))
            }
        }
    }
}
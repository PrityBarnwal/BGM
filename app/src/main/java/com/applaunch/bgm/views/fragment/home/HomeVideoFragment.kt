package com.applaunch.bgm.views.fragment.home

import android.content.Context
import android.content.pm.ActivityInfo
import android.net.Uri
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.applaunch.appbase.utils_base.gone
import com.applaunch.appbase.utils_base.visible
import com.applaunch.appbase.view_base.BaseFragment
import com.applaunch.bgm.BuildConfig
import com.applaunch.bgm.R
import com.applaunch.bgm.databinding.FragmentVideoFullScreenBinding
import com.applaunch.bgm.viewmodel.fragment.home.HomeVideoViewModel
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player

class HomeVideoFragment : BaseFragment<HomeVideoViewModel, FragmentVideoFullScreenBinding>() {
    override val layoutId: Int = R.layout.fragment_video_full_screen
    override val mViewModel: HomeVideoViewModel by viewModels()

    private var videoTitle = ""
    private var videoUrl = ""
    private var videoFav = false
    var isFullScreen = false
    private lateinit var exoPlayer: ExoPlayer

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (isFullScreen) {
                    activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                    isFullScreen = false
                } else {
                    findNavController().popBackStack()
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onFragmentCreated() {
        videoTitle = arguments?.getString("title") ?: ""
        videoUrl = arguments?.getString("videoUrl") ?: ""
        videoFav = (arguments?.getBoolean("isFav") ?: false)

        mViewBinding.clickListener = this
        mViewBinding.tvVideoTitle.text = videoTitle
        mViewBinding.isFavorite = videoFav
        videoAdjust()

    }


    override fun subscribeObservers() {
        //do letter
    }

    fun backPressed(view: View) {
        onManualBackPressed()
    }

    fun fullScreen(view: View) {
        if (!isFullScreen) {
            activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            mViewBinding.btnFullScreen.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_minimize
                )
            )
            isFullScreen = true
        } else {
            activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            mViewBinding.btnFullScreen.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_maximize
                )
            )
            isFullScreen = false
        }

    }

    private fun videoAdjust() {
        exoPlayer = ExoPlayer.Builder(requireContext()).setSeekBackIncrementMs(10000)
            .setSeekForwardIncrementMs(10000).build()
        mViewBinding.playerView.player = exoPlayer
        mViewBinding.playerView.keepScreenOn = true
        exoPlayer.addListener(object : Player.Listener {
            override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                if (playbackState == Player.STATE_BUFFERING) {
                    mViewBinding.progressBar.visible()
                } else if (playbackState == Player.STATE_READY) {
                    mViewBinding.progressBar.gone()
                }
            }
        })
        val videoSource = Uri.parse(BuildConfig.IMAGE_BASE_URL + videoUrl)
        val mediaItem = MediaItem.fromUri(videoSource)
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
        exoPlayer.play()
    }

    override fun onStop() {
        super.onStop()
        exoPlayer.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        exoPlayer.release()
    }

    override fun onPause() {
        super.onPause()
        exoPlayer.pause()
    }

}
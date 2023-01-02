package com.applaunch.bgm.views.fragment.home

import android.view.View
import androidx.fragment.app.viewModels
import com.applaunch.appbase.view_base.BaseFragment
import com.applaunch.bgm.R
import com.applaunch.bgm.databinding.FragmentWebinarBinding
import com.applaunch.bgm.state.fragment.home.WebinarState
import com.applaunch.bgm.viewmodel.fragment.home.WebinarViewModel

class WebinarFragment : BaseFragment<WebinarViewModel, FragmentWebinarBinding>() {
    private var webinarId = ""
    val stringBuilder = StringBuilder()
    override val layoutId: Int = R.layout.fragment_webinar
    override val mViewModel: WebinarViewModel by viewModels()
    override fun onFragmentCreated() {
        webinarId = arguments?.getString("webinar").toString()
        mViewBinding.ivWebinarBack.setOnClickListener { onManualBackPressed() }
        mViewModel.webinarId = webinarId
        mViewModel.getWebinar(iBaseRepoListener)
        mViewBinding.clickListener = this
    }

    override fun subscribeObservers() {
        mViewModel.stateObserver.observe(this) {
            when (it) {
                is WebinarState.SUCCESS -> {
                    mViewBinding.webinarContent = it.data
                    mViewModel.initWebinarList(it.data.description)

                    for (s in it.data.speakers) {
                        stringBuilder.append(" ").append(s).append("\n")
                    }
                    mViewBinding.tvIvan.text= stringBuilder.toString()
                }

                is WebinarState.ERROR -> {
                    it.msg
                }
                is WebinarState.WebinarList ->{
                    mViewBinding.recWebinar.adapter = it.adapter
                }
            }
        }
    }
    fun onClick(view:View){
        onManualBackPressed()
    }
}
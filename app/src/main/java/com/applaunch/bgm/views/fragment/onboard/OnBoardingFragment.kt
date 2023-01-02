package com.applaunch.bgm.views.fragment.onboard

import android.view.View
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.applaunch.appbase.utils_base.Print
import com.applaunch.appbase.utils_base.navigateNew
import com.applaunch.appbase.utils_base.navigateTo
import com.applaunch.appbase.utils_base.onClick
import com.applaunch.appbase.view_base.BaseFragment
import com.applaunch.bgm.R
import com.applaunch.bgm.adapter.viewpager.ViewPagerAdapter
import com.applaunch.bgm.databinding.OnboardingFragmentBinding
import com.applaunch.bgm.state.fragment.OnBoardState
import com.applaunch.bgm.utils.Constant
import com.applaunch.bgm.viewmodel.fragment.onboard.OnBoardViewModel

class OnBoardingFragment : BaseFragment<OnBoardViewModel, OnboardingFragmentBinding>() {
    override val mViewModel: OnBoardViewModel by viewModels()
    override val layoutId: Int = R.layout.fragment_on_boarding

    override fun onFragmentCreated() {
        mViewBinding.clickListener = this
        mViewBinding.position = Constant.Count.zero
        intiViewPager()

        mViewBinding.ivNext.onClick{
            mViewBinding.viewPager.currentItem++

            Print.log("Current : ${mViewBinding.viewPager.currentItem}")

            if (mViewBinding.viewPager.currentItem == 2) {
                navigateNew(R.id.accessOnBoardingFragment)
            }
        }
    }

    override fun subscribeObservers() {
        mViewModel.stateObserver.observe(this) {
            when (it) {
                OnBoardState.NavigateToAccess ->{
                    Print.log("NavigateToAccess")
                    navigateTo(R.id.accessOnBoardingFragment)
                }
            }
        }
    }
    private fun intiViewPager() {
        val pagerAdapter = activity?.let { ViewPagerAdapter(it) }!!
        pagerAdapter.addFragment(mViewModel.getOnBoardSlider(Constant.Count.zero))
        pagerAdapter.addFragment(mViewModel.getOnBoardSlider(Constant.Count.one))
        pagerAdapter.addFragment(mViewModel.getOnBoardSlider(Constant.Count.two))
        mViewBinding.viewPager.adapter = pagerAdapter

        mViewBinding.dotIndicator.setViewPager2(mViewBinding.viewPager)

        mViewBinding.viewPager.isUserInputEnabled = false
        mViewBinding.viewPager.isSaveEnabled = false
        mViewBinding.viewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                mViewBinding.position = position
                Print.log("Current Page position : $position")
            }
        })
    }

    fun onSkipClicked(view: View) {
        navigateTo(R.id.accessOnBoardingFragment)
    }
}
package com.applaunch.bgm.views.activity

import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.applaunch.appbase.utils_base.navigateTo
import com.applaunch.appbase.view_base.BaseActivity
import com.applaunch.bgm.R
import com.applaunch.bgm.databinding.MainActivityBinding
import com.applaunch.bgm.state.activity.MainActivityState
import com.applaunch.bgm.viewmodel.activty.MainActivityViewModel
import doNothing

class MainActivity(override val layoutId: Int? = R.layout.activity_main) :
    BaseActivity<MainActivityViewModel, MainActivityBinding>() {

    private lateinit var navController: NavController
    override val mViewModel: MainActivityViewModel by viewModels()

    override fun onInitialize() {
        val mainNavHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = mainNavHostFragment.navController

        mViewModel.initBottomNav(navController)
        mViewModel.bottomNavItemClick(mViewBinding.navView)

    }

    override fun subscribeObserver() {
        mViewModel.stateObserver.observe(this) {
            when (it) {
                is MainActivityState.BottomNav -> {
                    mViewBinding.isNavVisible = it.isBottomNavVisible
                }
                is MainActivityState.BottomNavFragment -> navController.navigateTo(it.fragId)
                else -> {
                    doNothing()
                }
            }
        }
    }
}
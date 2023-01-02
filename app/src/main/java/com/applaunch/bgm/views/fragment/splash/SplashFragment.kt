package com.applaunch.bgm.views.fragment.splash

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.applaunch.appbase.utils_base.navigateNew
import com.applaunch.appbase.utils_base.session.SessionManager
import com.applaunch.appbase.view_base.BaseFragment
import com.applaunch.bgm.R
import com.applaunch.bgm.databinding.SplashFragmentBinding
import com.applaunch.bgm.state.fragment.splash.SplashState
import com.applaunch.bgm.utils.session.SessionConstant
import com.applaunch.bgm.viewmodel.fragment.splash.SplashViewModel
import doNothing
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class SplashFragment : BaseFragment<SplashViewModel,SplashFragmentBinding>() {
    override val mViewModel:SplashViewModel by viewModels()
    override val layoutId: Int = R.layout.fragment_splash


    override fun onFragmentCreated() {
        val session = SessionManager(requireContext())

        lifecycleScope.launch(Dispatchers.IO){
           val accessToken = async {
               session.getPreference(SessionConstant.ACCESS_TOKEN, "").first()
           }
            mViewModel.checkIfUserLoggedIn(accessToken.await())
        }

    }

    override fun subscribeObservers() {
        mViewModel.stateObserver.observe(this){
            when(it){
                is SplashState.NavigateToOnboarding -> {
                    navigateNew( R.id.onBoardingFragment)
                }

                is SplashState.NavigateToHome -> {
                    navigateNew(R.id.navigation_home)
                }
                else -> {doNothing()}
            }
        }
    }

}
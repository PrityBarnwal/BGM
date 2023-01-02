package com.applaunch.bgm.views.fragment.access_onboard

import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View
import androidx.fragment.app.viewModels
import com.applaunch.appbase.utils_base.*
import com.applaunch.appbase.utils_base.session.SessionManager
import com.applaunch.appbase.view_base.BaseFragment
import com.applaunch.bgm.R
import  com.applaunch.bgm.databinding.AccessOnBoardBinding
import com.applaunch.bgm.state.access_onboard.AccessOnboardState
import com.applaunch.bgm.utils.Constant
import com.applaunch.bgm.viewmodel.fragment.access_onboard.AccessOnBoardViewModel
import doNothing
import regexChecker

class AccessOnBoardingFragment : BaseFragment<AccessOnBoardViewModel,AccessOnBoardBinding>() {

    override val mViewModel: AccessOnBoardViewModel by viewModels()
    override val layoutId: Int = R.layout.fragment_access_on_boarding

    override fun onFragmentCreated() {
        mViewBinding.isInvalidPassword = false
        mViewBinding.clickListner = this
        setCheckboxTextColor()

        mViewBinding.etAccess.afterTextChanged {
            mViewModel.authRequest.accessCode = it
            mViewBinding.isValid = isValid()
        }
        mViewBinding.ivBack.onClick{
           navigateTo(R.id.onBoardingFragment)
        }
    }

    override fun subscribeObservers() {
        mViewModel.stateObserver.observe(this) {
            when (it) {
                is AccessOnboardState.SUCCESS -> {
                    val session = SessionManager(requireContext())
                    mViewModel.saveUserData(session, it.data.data)
                    doNothing()
                }

                is AccessOnboardState.ERROR -> {
                    Print.log("AccessOnboardState.ERROR $it")
                    mViewBinding.isInvalidPassword = true
                    mViewBinding.errorMessage = it.msg
                }
                is AccessOnboardState.NavigateToAccountActivated -> {
                    navigateNew(R.id.accountActivatedFragment)
                }
                else -> {
                    doNothing()
                }
            }
        }
    }

    fun onCbTermsClicked(view: View) {
        mViewBinding.isValid = isValid()
    }

    fun isValid(): Boolean {
        return (mViewBinding.cbTermAndCondition.isChecked &&
                mViewBinding.etAccess.isNN() &&
                mViewBinding.etAccess.getValue()
                    .regexChecker(Constant.RegexPattern.AccessCodePattern))

    }

    fun onContinueClick(view: View) {
        mViewModel.login(iBaseRepoListener)
    }


    private fun setCheckboxTextColor() {
        val clickableSpan1 = object : ClickableSpan() {
            override fun onClick(widget: View) {
                //ignore
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = false
            }
        }
        val clickableSpan2 = object : ClickableSpan() {
            override fun onClick(widget: View) {
                //ignore
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = false
            }
        }
        val loginTermCondition = getString(R.string.i_agree_to_the_terms_of_use_and_privacy_policy)
        val spannableStringLogin = SpannableString(loginTermCondition)
        spannableStringLogin.setSpan(
            clickableSpan1, 15, 27, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannableStringLogin.setSpan(
            clickableSpan2, 31, 47, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        mViewBinding.tvTermCondition.text = spannableStringLogin
    }


    override fun onResume() {
        super.onResume()
        Print.log("Data  =============")
    }

}
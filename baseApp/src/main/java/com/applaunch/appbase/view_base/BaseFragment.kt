package com.applaunch.appbase.view_base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.applaunch.appbase.R
import com.applaunch.appbase.listner_base.BaseRepoListener
import com.applaunch.appbase.utils_base.*
import com.applaunch.appbase.utils_base.session.SessionManager
import com.applaunch.appbase.viewmodel_base.BaseViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

abstract class BaseFragment<VM : BaseViewModel<*>, VB : ViewDataBinding> : Fragment(),
    BaseRepoListener {

    protected abstract val mViewModel: BaseViewModel<*>

    @get:LayoutRes
    abstract val layoutId: Int

    abstract fun onFragmentCreated()

    abstract fun subscribeObservers()

    lateinit var mViewBinding: VB

    lateinit var iBaseRepoListener: BaseRepoListener

    private val progressBar: CustomProgressBar by lazy {
        CustomProgressBar()
    }

    val sessionManager: SessionManager by lazy {
        SessionManager(requireContext())
    }

    val baseCodeSnippet: BaseCodeSnippet by lazy {
        BaseCodeSnippet(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (::mViewBinding.isInitialized.not()) {
            mViewBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
            mViewBinding.lifecycleOwner = viewLifecycleOwner
        }
        return mViewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            async { subscribeObservers()
            }.await()

            iBaseRepoListener = this@BaseFragment

            mViewModel.onInitialized(arguments)

            onFragmentCreated()

            initLoader()
        }
    }

    private fun initLoader() {
        mViewModel.baseLiveData.observe(viewLifecycleOwner) {
            when (it.first) {
                BaseConstants.BaseKeys.SHOW_LOADER -> showLoader()
                BaseConstants.BaseKeys.SHOW_LOADER_MESSAGE -> showMessage(it.second as String)
                BaseConstants.BaseKeys.HIDE_LOADER -> hideLoader()
                BaseConstants.BaseKeys.SHOW_MESSAGE -> showMessage(it.second as String)
            }
        }
    }


    override fun showLoader() {
        showProgressBar(progressBar,"Loading....")
    }

    override fun hideLoader() {
        dismissProgressBar(progressBar)
    }

    override fun showMessage(message: String) {
        toastMessage(message)
    }

    override fun isNetworkConnected(): Boolean {
        val isConnected = NetworkConnection(requireContext()).hasNetworkConnection()
        if (isConnected.not()) {
            showMessage(getString(R.string.not_internet_connection))
            Print.log(getString(R.string.not_internet_connection))
        }
        return isConnected
    }

    override fun showErrorMessage(message: String) {
        Print.log("Error Message => $message")
        showBottomDialog(message)
    }
    fun Fragment.onManualBackPressed() {
        activity?.onBackPressed()
    }

}
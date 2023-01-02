package com.applaunch.bgm.views.fragment.article

import androidx.fragment.app.viewModels
import com.applaunch.appbase.view_base.BaseFragment
import com.applaunch.bgm.R
import com.applaunch.bgm.databinding.FragmentArticleBinding
import com.applaunch.bgm.state.fragment.article.ArticleFragmentState
import com.applaunch.bgm.viewmodel.fragment.article.ArticleViewModel

class ArticleFragment :BaseFragment<ArticleViewModel, FragmentArticleBinding>() {
    private var articleId = ""
    override val layoutId: Int = R.layout.fragment_article
    override val mViewModel:ArticleViewModel by viewModels()
    override fun onFragmentCreated() {
        articleId= arguments?.getString("article") ?:""
        mViewBinding.ivArticleBack.setOnClickListener { onManualBackPressed() }
        mViewModel.articleId =articleId
        mViewModel.getArticle(iBaseRepoListener)
    }
    override fun subscribeObservers() {
        mViewModel.stateObserver.observe(this) {
            when (it) {
                is ArticleFragmentState.SUCCESS -> {
                    mViewBinding.articleContent = it.data
                    mViewModel.initArticleList(it.data.description)
                }
                is ArticleFragmentState.ERROR -> {
                    it.msg
                }
                is ArticleFragmentState.ArticleList -> mViewBinding.recArticle.adapter = it.adapter
            }
        }
    }
}

package com.applaunch.bgm.viewmodel.fragment.article

import android.os.Bundle
import androidx.lifecycle.viewModelScope
import com.applaunch.appbase.listner_base.BaseRepoListener
import com.applaunch.appbase.model_base.State
import com.applaunch.appbase.viewmodel_base.BaseViewModel
import com.applaunch.bgm.adapter.recycler.adapter.ArticleAdapter
import com.applaunch.bgm.model.dto.article_data.ArticleDescriptionModel
import com.applaunch.bgm.network.NetworkRepository
import com.applaunch.bgm.state.fragment.article.ArticleFragmentState
import doNothing
import kotlinx.coroutines.launch

class ArticleViewModel : BaseViewModel<ArticleFragmentState>(){
    var articleId: String=""
    lateinit var articleAdapter: ArticleAdapter
    var articleFragmentState: ArticleFragmentState = ArticleFragmentState.Init
        set(value) {
            field = value
            publishState(articleFragmentState)
        }
    override fun onInitialized(bundle: Bundle?) {
        //do letter
    }
    fun getArticle(baseRepoListener: BaseRepoListener) {
        viewModelScope.launch {
            NetworkRepository(baseRepoListener).article(articleId).collect {
                when (it) {
                    is State.Success -> {
                        articleFragmentState = ArticleFragmentState.SUCCESS(it.data.data)
                    }
                    is State.Error -> {
                        articleFragmentState = ArticleFragmentState.ERROR(it.message)
                    }
                    else -> {
                        doNothing()
                    }
                }
            }
        }
    }
    fun initArticleList(dataList: ArrayList<ArticleDescriptionModel>) {
        articleAdapter = ArticleAdapter(dataList, this)
        articleFragmentState = ArticleFragmentState.ArticleList(articleAdapter)
    }

}
package com.applaunch.bgm.state.fragment.article

import com.applaunch.bgm.adapter.recycler.adapter.ArticleAdapter
import com.applaunch.bgm.model.dto.article_data.ArticleDataModel

sealed class ArticleFragmentState {
    object Init:ArticleFragmentState()
    data class SUCCESS(val data: ArticleDataModel) : ArticleFragmentState()
    data class ERROR(val msg: String) : ArticleFragmentState()
    data class ArticleList(val adapter: ArticleAdapter):ArticleFragmentState()
}
package com.applaunch.bgm.adapter.recycler.adapter

import android.view.ViewGroup
import com.applaunch.appbase.adapter_base.BaseRecyclerAdapter
import com.applaunch.bgm.R
import com.applaunch.bgm.adapter.recycler.viewholder.ArticleViewHolder
import com.applaunch.bgm.databinding.ItemListArticleBinding
import com.applaunch.bgm.model.dto.article_data.ArticleDescriptionModel
import com.applaunch.bgm.viewmodel.fragment.article.ArticleViewModel

class ArticleAdapter(
    list: MutableList<ArticleDescriptionModel>,
    var articleViewModel: ArticleViewModel,
) : BaseRecyclerAdapter<ArticleDescriptionModel, ArticleViewHolder>(list) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            inflateDataBinding(R.layout.item_list_article, parent) as ItemListArticleBinding,
            articleViewModel
        )
    }
}

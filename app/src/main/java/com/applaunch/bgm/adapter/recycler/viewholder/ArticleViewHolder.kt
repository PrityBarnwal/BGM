package com.applaunch.bgm.adapter.recycler.viewholder

import com.applaunch.appbase.adapter_base.BaseViewHolder
import com.applaunch.bgm.databinding.ItemListArticleBinding
import com.applaunch.bgm.model.dto.article_data.ArticleDescriptionModel
import com.applaunch.bgm.utils.Constant
import com.applaunch.bgm.viewmodel.fragment.article.ArticleViewModel

class ArticleViewHolder(
    var binding: ItemListArticleBinding,
    var viewModel: ArticleViewModel,
) : BaseViewHolder<ArticleDescriptionModel, ItemListArticleBinding>(binding) {
    override fun populateData(data: ArticleDescriptionModel, position: Int) {

        binding.articleList = data
        when (data.type) {
            Constant.ArticleType.TEXT -> binding.articleType = Constant.ArticleType.TEXT
            Constant.ArticleType.IMAGE -> binding.articleType = Constant.ArticleType.IMAGE
            else -> binding.articleType = Constant.ArticleType.PARAGRAPH
        }
    }
}
package com.holpackapooolka.holidayartponddos.nevs

import androidx.recyclerview.widget.DiffUtil

class NevsDiffUtilListAdapter: DiffUtil.ItemCallback<ArticleUIModel>() {
    override fun areItemsTheSame(oldItem: ArticleUIModel, newItem: ArticleUIModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ArticleUIModel, newItem: ArticleUIModel): Boolean {
        return oldItem == newItem
    }

}
package com.holpackcand.holidayartcinoda.nevs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.holpackcand.holidayartcinoda.R
import com.holpackcand.holidayartcinoda.databinding.NevsSingleItemBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NevsListAdapter:
    ListAdapter<ArticleUIModel, NevsListAdapter.NevsRecVievHolder>(
        NevsDiffUtilListAdapter()
    ) {

    private var onItemNevsClickListener : ((article: ArticleUIModel) -> Unit)? = null

    inner class NevsRecVievHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = NevsSingleItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NevsRecVievHolder {
        LayoutInflater.from(parent.context)
            .inflate(R.layout.nevs_single_item, parent, false).also {
                return NevsRecVievHolder(it)
            }
    }

    override fun onBindViewHolder(holder: NevsRecVievHolder, position: Int) {
        val currentItem = getItem(position)
        val currentUriImg = currentItem.urlToImage


        holder.binding.apply {
            tvTitle.text = currentItem.title
            tvDescription.text = currentItem.description
            tvSource.text = currentItem.source
            tvPublishedAt.text = currentItem.publishedAt

            if (currentUriImg == "no") {
                ivArticleImage.setImageResource(R.drawable.news)
            } else {
                CoroutineScope(Dispatchers.IO).launch {
                    ivArticleImage.load(currentUriImg) {
                        placeholder(R.drawable.ic_baseline_downloading_24)
                    }
                }
            }
            root.setOnClickListener {
                onItemNevsClickListener?.invoke(currentItem)
            }
        }
    }
    fun setOnItemClickListener (listener: (article: ArticleUIModel)-> Unit){
        onItemNevsClickListener = listener
    }
}
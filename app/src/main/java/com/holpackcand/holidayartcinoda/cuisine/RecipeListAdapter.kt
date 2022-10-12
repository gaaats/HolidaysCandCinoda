package com.holidayscountrypackseven.holidaycanadaart.recycler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.holpackcand.holidayartcinoda.R
import com.holpackcand.holidayartcinoda.cuisine.RecipesListNetItem
import com.holpackcand.holidayartcinoda.databinding.SingleFoodBinding


class RecipeListAdapter() :
    ListAdapter<RecipesListNetItem, RecipeListAdapter.HolidaysVievHolder>(RecipesDiffUtill()) {

    val listImages = listOf(
        R.drawable.f1,
        R.drawable.f2,
        R.drawable.f3,
        R.drawable.f4,
        R.drawable.f5,
    )

    private var onItemClickListener: ((holiday: RecipesListNetItem) -> Unit)? = null

    class HolidaysVievHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = SingleFoodBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolidaysVievHolder {
        LayoutInflater.from(parent.context)
            .inflate(R.layout.single_food, parent, false).also {
                return HolidaysVievHolder(it)
            }
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: HolidaysVievHolder, position: Int) {
        val currentImg = listImages.random()
        val currentItem = getItem(position)
        holder.binding.apply {
            tvName.text = currentItem._title
            root.setOnClickListener {
                onItemClickListener?.invoke(currentItem)
            }
            imgOneFood.setImageResource(currentImg)
        }
    }

    fun setOnItemClickListener(listener: (holidayName: RecipesListNetItem) -> Unit) {
        onItemClickListener = listener
    }
}
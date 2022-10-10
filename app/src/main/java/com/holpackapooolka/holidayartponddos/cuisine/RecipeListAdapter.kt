package com.holidayscountrypackseven.holidaycanadaart.recycler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.holpackapooolka.holidayartponddos.R
import com.holpackapooolka.holidayartponddos.cuisine.RecipesListNetItem
import com.holpackapooolka.holidayartponddos.databinding.SingleFoodBinding


class RecipeListAdapter() :
    ListAdapter<RecipesListNetItem, RecipeListAdapter.HolidaysVievHolder>(RecipesDiffUtill()) {

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
        val currentItem = getItem(position)
        holder.binding.apply {
            tvName.text = currentItem._title
            tvIngridients.text = currentItem._ingredients
            root.setOnClickListener {
                onItemClickListener?.invoke(currentItem)
            }
        }
    }

    fun setOnItemClickListener(listener: (holidayName: RecipesListNetItem) -> Unit) {
        onItemClickListener = listener
    }
}
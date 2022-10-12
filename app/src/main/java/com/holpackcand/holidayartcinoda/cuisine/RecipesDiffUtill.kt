package com.holidayscountrypackseven.holidaycanadaart.recycler
import androidx.recyclerview.widget.DiffUtil
import com.holpackcand.holidayartcinoda.cuisine.RecipesListNetItem

class RecipesDiffUtill: DiffUtil.ItemCallback<RecipesListNetItem>() {
    override fun areItemsTheSame(oldItem: RecipesListNetItem, newItem: RecipesListNetItem): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: RecipesListNetItem, newItem: RecipesListNetItem): Boolean {
        return oldItem == newItem
    }
}
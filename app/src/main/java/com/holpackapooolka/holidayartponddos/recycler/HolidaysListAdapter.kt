package com.holpackapooolka.holidayartponddos.recycler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.holpackapooolka.holidayartponddos.R
import com.holpackapooolka.holidayartponddos.databinding.SingleHolidayBinding
import com.holpackapooolka.holidayartponddos.entity.HolideyItemUI


class HolidaysListAdapter() :
    ListAdapter<HolideyItemUI, HolidaysListAdapter.HolidaysVievHolder>(HolidaysDiffUtill()) {

    private var onItemClickListener: ((holiday: HolideyItemUI) -> Unit)? = null

    class HolidaysVievHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = SingleHolidayBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolidaysVievHolder {
        LayoutInflater.from(parent.context)
            .inflate(R.layout.single_holiday, parent, false).also {
                return HolidaysVievHolder(it)
            }
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: HolidaysVievHolder, position: Int) {
        val currentItem = getItem(position)
        holder.binding.apply {
            tvName.text = currentItem.name
            tvDate.text = currentItem.date
            tvDay.text = currentItem.day
            root.setOnClickListener {
                onItemClickListener?.invoke(currentItem)
            }
        }
    }

    fun setOnItemClickListener(listener: (holidayName: HolideyItemUI) -> Unit) {
        onItemClickListener = listener
    }
}
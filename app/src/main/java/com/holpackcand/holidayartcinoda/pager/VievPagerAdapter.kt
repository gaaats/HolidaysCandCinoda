package com.holpackcand.holidayartcinoda.pager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.holpackcand.holidayartcinoda.R
import com.holpackcand.holidayartcinoda.databinding.ItemVievpagerHolderBinding

class VievPagerAdapter (val list: List<Int>): RecyclerView.Adapter<VievPagerAdapter.VievPagerHolder>() {

    inner class VievPagerHolder (view: View): RecyclerView.ViewHolder(view){

        val binding = ItemVievpagerHolderBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VievPagerHolder {
        val viev = LayoutInflater.from(parent.context).inflate(R.layout.item_vievpager_holder, parent, false)
        return VievPagerHolder(viev)
    }

    override fun onBindViewHolder(holder: VievPagerHolder, position: Int) {
        holder.binding.imageViev.setImageResource(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
package com.polotika.brokerage.pojo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.polotika.brokerage.R
import com.polotika.brokerage.databinding.ServiceItemBinding
import com.polotika.brokerage.pojo.models.ServiceItem

class ServicesRecyclerViewAdapter(private var list: List<ServiceItem>? = emptyList()) :
    RecyclerView.Adapter<ServicesRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ServiceItemBinding>(
            inflater,
            R.layout.service_item,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list?.get(position)
        holder.bind(item!!)
    }

    override fun getItemCount() = list?.size ?: 0

    class ViewHolder(val binding: ServiceItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ServiceItem) {
            binding.item = item
            binding.invalidateAll()
        }
    }

    fun changeData(list: List<ServiceItem>) {
        this.list = list
        notifyDataSetChanged()
    }
}
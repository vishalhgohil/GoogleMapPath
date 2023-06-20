package com.app.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.databinding.RowLocationListBinding
import com.app.db.entity.LocationDetailsRoom

class LocationListAdapter(val arrayList: ArrayList<LocationDetailsRoom>) : RecyclerView.Adapter<LocationListAdapter.ViewHolder>() {

    inner class ViewHolder(val binding :RowLocationListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) = with(binding){
            val item = arrayList[position]
            txtName.text = item.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RowLocationListBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }
}
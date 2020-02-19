package com.example.taskauto.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.taskauto.R
import com.example.taskauto.databinding.ItemManufactDetailRecyclerBinding
import com.example.taskauto.model.room.ModelEntity
import com.example.taskauto.util.DiffUtilCallBackDetailMan

import com.example.taskauto.listeners.ItemClickListener


class DetailManListAdapter(private var onItemClickListener: ItemClickListener):
    ListAdapter<ModelEntity, DetailManListAdapter.DetailListViewHolder>(
        DiffUtilCallBackDetailMan()
    ) {

    class DetailListViewHolder(
        private var detailBinding: ItemManufactDetailRecyclerBinding,
       onItemClickListener: ItemClickListener
    ) : RecyclerView.ViewHolder(detailBinding.root) {

        init {
            itemView.setOnClickListener {
                onItemClickListener.onItemClick(adapterPosition)
            }
        }

        fun bind(itemView: ModelEntity) {
            detailBinding.modelEntity = itemView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemManufactDetailRecyclerBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_manufact_detail_recycler, parent, false)
        return DetailListViewHolder(binding, onItemClickListener)
    }

    override fun onBindViewHolder(holder: DetailListViewHolder, position: Int) {
        val currentData = getItem(position)
        holder.bind(currentData)
    }
}








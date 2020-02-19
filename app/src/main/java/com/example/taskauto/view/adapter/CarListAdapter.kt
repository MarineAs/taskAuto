package com.example.taskauto.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.taskauto.R
import com.example.taskauto.databinding.ItemRecyclerBinding
import com.example.taskauto.listeners.ItemClickListener
import com.example.taskauto.model.room.CarEntity
import com.example.taskauto.util.DiffUtilCallBack


class CarListAdapter(private val onItemClickListener: ItemClickListener) :
    ListAdapter<CarEntity, CarListAdapter.CarListViewHolder>(DiffUtilCallBack()) {

    class CarListViewHolder(
        private val carDataBinding: ItemRecyclerBinding,
        onItemClickListener: ItemClickListener
    ) : RecyclerView.ViewHolder(carDataBinding.root) {

        init {
            carDataBinding.clickHandler = onItemClickListener
        }

        fun bind(item: CarEntity) {
            carDataBinding.carEntity = item
            carDataBinding.position = adapterPosition
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemRecyclerBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_recycler, parent, false)

        return CarListViewHolder(
            binding, onItemClickListener
        )
    }

    override fun onBindViewHolder(holder: CarListViewHolder, position: Int) {
        val currentCarData: CarEntity = getItem(position)
        holder.bind(currentCarData)
    }
}

package com.example.taskauto.util

import androidx.recyclerview.widget.DiffUtil
import com.example.taskauto.model.room.CarEntity

class DiffUtilCallBack() : DiffUtil.ItemCallback<CarEntity>() {

    override fun areItemsTheSame(oldItem: CarEntity, newItem: CarEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CarEntity, newItem: CarEntity): Boolean {
        return oldItem.price == newItem.price && oldItem.year == newItem.year &&
                oldItem.manName == newItem.manName && oldItem.modelName == newItem.modelName
    }

}

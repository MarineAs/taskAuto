package com.example.taskauto.util

import androidx.recyclerview.widget.DiffUtil
import com.example.taskauto.model.room.ModelEntity

class DiffUtilCallBackDetailMan : DiffUtil.ItemCallback<ModelEntity>() {
    override fun areItemsTheSame(
        oldItem: ModelEntity,
        newItem: ModelEntity
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: ModelEntity,
        newItem: ModelEntity
    ): Boolean {
        return oldItem.manName == newItem.manName
    }
}
package com.example.taskauto.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.taskauto.R
import com.example.taskauto.databinding.ItemModelDetailRecyclerBinding
import com.example.taskauto.listeners.ItemClickListener

class DetailModelListAdapter(
    private var onItemClickListener: ItemClickListener

) : RecyclerView.Adapter<DetailModelListAdapter.DetailListViewHolder>() {
    private var dataList: List<String> = arrayListOf()
    class DetailListViewHolder(
        private var dataBiding: ItemModelDetailRecyclerBinding,
        onItemClickListener: ItemClickListener
    ) : RecyclerView.ViewHolder(dataBiding.root) {
        init {
            dataBiding.clickHandler = onItemClickListener
        }

        fun bind(itemView: String) {
            dataBiding.modelName = itemView
            dataBiding.position = adapterPosition
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemModelDetailRecyclerBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.item_model_detail_recycler,
            parent,
            false
        )

        return DetailListViewHolder(binding, onItemClickListener)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: DetailListViewHolder, position: Int) {
        val currentData = dataList[position]
        holder.bind(currentData)
    }

    fun setDataList(dataList: List<String>) {
        this.dataList = dataList
        notifyDataSetChanged()
    }
}
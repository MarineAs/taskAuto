package com.example.taskauto.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskauto.R
import com.example.taskauto.model.room.CarEntity
import com.example.taskauto.listeners.ItemClickListener
import com.example.taskauto.view.adapter.CarListAdapter
import com.example.taskauto.listeners.EditInterface
import com.example.taskauto.viewModel.MainFragmentViewModel

class MainFragment : Fragment(),
    ItemClickListener {

    companion object {
        fun getInstance(): MainFragment = MainFragment()
    }

    private lateinit var carViewModel: MainFragmentViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CarListAdapter
    private lateinit var btnAdd: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.main_fragment, container, false)
        carViewModel = ViewModelProvider(this).get(MainFragmentViewModel::class.java)
        recyclerView = view.findViewById(R.id.recyclerView)
        btnAdd = view.findViewById(R.id.btnAdd)

        setAdapter()
        itemTouchHelper()
        observeObservers()

        btnAdd.setOnClickListener {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.fragmntContainer, AddFragment.getInstance(), "add")
                ?.addToBackStack(null)
                ?.commit()
        }

        return view
    }

    private fun setAdapter() {
        adapter = CarListAdapter(this)
        recyclerView.adapter = adapter
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
    }

    private fun observeObservers() {
        carViewModel.allData?.observe(viewLifecycleOwner,
            Observer<List<CarEntity>> { t -> adapter.submitList(t) })
    }

    override fun onItemClick(position: Int) {
        val frEdit = AddFragment.getInstance()

        frEdit.editCar = adapter.currentList[position]

        (activity as? EditInterface)?.setEditData(adapter.currentList[position])
        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(R.id.fragmntContainer, frEdit, "add")
            ?.addToBackStack(null)
            ?.commit()
    }

    private fun itemTouchHelper() {
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                adapter.currentList[viewHolder.adapterPosition].id?.let { carViewModel.delete(it) }
            }
        }).attachToRecyclerView(recyclerView)
    }
}
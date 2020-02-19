package com.example.taskauto.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskauto.R
import com.example.taskauto.listeners.ItemClickListener
import com.example.taskauto.view.adapter.DetailModelListAdapter
import com.example.taskauto.viewModel.DetailModelFragmentViewModel
import com.example.taskauto.listeners.ModelInterface
import java.util.ArrayList

class DetailModelFragment : Fragment(),
    ItemClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DetailModelListAdapter
    private lateinit var bindingData: DetailModelFragmentViewModel
    var model: List<String>? = ArrayList()


    companion object {
        fun getInstance(): DetailModelFragment {
            return DetailModelFragment()
        }
    }

    fun sendData(data: List<String>?) {
        model = data
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.model_detail_fragment, container, false)
        recyclerView = view.findViewById(R.id.detailRecycler)
        bindingData = ViewModelProvider(this).get(DetailModelFragmentViewModel::class.java)

        setAdapter()
        observeObservers()
        return view
    }

    private fun setAdapter() {
        adapter = DetailModelListAdapter(this)
        recyclerView.adapter = adapter
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
    }

    private fun observeObservers() {
        bindingData.allData.value = model
        bindingData.allData.observe(viewLifecycleOwner,
            Observer<List<String>> { t ->
                adapter.setDataList(t)
            })
    }

    override fun onItemClick(position: Int) {
        (activity as? ModelInterface)?.setModelName(bindingData.allData.value?.get(position))
        activity?.supportFragmentManager?.popBackStack()
    }
}


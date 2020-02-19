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
import com.example.taskauto.model.room.ModelEntity
import com.example.taskauto.listeners.ItemClickListener
import com.example.taskauto.view.adapter.DetailManListAdapter
import com.example.taskauto.listeners.ManInterface
import com.example.taskauto.viewModel.DetailManFragmentViewModel


class DetailManFragment : Fragment(),
    ItemClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DetailManListAdapter
    private lateinit var bindingViewModel: DetailManFragmentViewModel

    companion object {
        fun getInstance() =
            DetailManFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.manufact_detail_fragment, container, false)

        recyclerView = view.findViewById(R.id.detailRecycler)
        bindingViewModel =
            ViewModelProvider(this).get(DetailManFragmentViewModel::class.java)

        setAdapter()
        observeViewModel()

        return view
    }

    private fun setAdapter() {
        adapter = DetailManListAdapter(this)
        recyclerView.adapter = adapter
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager

    }

    private fun observeViewModel() {
        bindingViewModel.allData?.observe(viewLifecycleOwner,
            Observer<List<ModelEntity>> { t ->
                adapter.submitList(t)
            })
    }

    override fun onItemClick(position: Int) {
        (activity as? ManInterface)?.setManName(bindingViewModel.allData?.value?.get(position)?.manName,
            bindingViewModel.allData?.value?.get(position)?.modelNames)
        activity?.onBackPressed()
    }
}


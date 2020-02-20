package com.example.taskauto.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.taskauto.R
import com.example.taskauto.databinding.AddFragmentBinding
import com.example.taskauto.listeners.EditInterface
import com.example.taskauto.listeners.ManInterface
import com.example.taskauto.listeners.ModelInterface
import com.example.taskauto.model.room.CarEntity
import com.example.taskauto.viewModel.AddFragmentViewModel

class AddFragment : Fragment(), ManInterface,
    ModelInterface, EditInterface {
    private lateinit var viewModel: AddFragmentViewModel
    var editCar: CarEntity = CarEntity()

    companion object {
        fun getInstance() = AddFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(AddFragmentViewModel::class.java)

        val dataBinding: AddFragmentBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.add_fragment,
            container,
            false
        )

        if (editCar.id != null && viewModel.manName.value == null) {
            viewModel.setCarData(editCar)
        }

        dataBinding.viewModel = viewModel
        dataBinding.lifecycleOwner = viewLifecycleOwner

        viewModel.manName.observe(viewLifecycleOwner, Observer {
            if (it != editCar.manName) {
                viewModel.setModelName(viewModel.modelList?.value?.get(0))
                editCar.manName = it
            }
        })

        viewModel.clickedFragment.observe(viewLifecycleOwner, Observer {
            if (it == "DetailManFragment") {
                openManufactFragment()
            }
            if (it == "DetailModelFragment") {
                if (viewModel.modelList?.value.isNullOrEmpty()) {
                    viewModel.getModel()
                }
                openModelFragment()
                viewModel.clickedFragment.value = null
            }
        })

        viewModel.backstack.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                activity?.supportFragmentManager?.popBackStack()
                viewModel.backstack.value = false
            }
        })

        viewModel.emptyError?.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                toast(it)
            }
        })

        viewModel.selectError?.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                toast(it)
            }
        })

        return dataBinding.root
    }

    override fun setManName(string: String?, data: List<String>?) {
        viewModel.setManName(string)
        viewModel.setModelsData(data)
    }

    override fun setModelName(string: String?) {
        viewModel.setModelName(string)
    }

    override fun setEditData(data: CarEntity) {
        viewModel.setCarData(data)
    }

    private fun toast(string: String) {
        Toast.makeText(context, string, Toast.LENGTH_SHORT).show()
    }

    private fun openManufactFragment() {
        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(R.id.fragmntContainer, DetailManFragment.getInstance())
            ?.addToBackStack(null)
            ?.commit()
    }

    private fun openModelFragment() {
        val frModel: DetailModelFragment = DetailModelFragment.getInstance()

        frModel.sendData(viewModel.modelList?.value)
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragmntContainer, frModel, "model")
            ?.addToBackStack(null)
            ?.commit()
    }
}
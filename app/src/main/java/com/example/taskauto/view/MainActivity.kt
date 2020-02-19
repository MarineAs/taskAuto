package com.example.taskauto.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.taskauto.R
import com.example.taskauto.model.room.CarEntity
import com.example.taskauto.listeners.EditInterface
import com.example.taskauto.listeners.ManInterface
import com.example.taskauto.listeners.ModelInterface


class MainActivity : AppCompatActivity(),ManInterface,
    ModelInterface, EditInterface {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragmntContainer, MainFragment.getInstance(), "main").commit()
    }

    override fun setManName(string: String?, data: List<String>?) {
        findAddFragment()?.setManName(string, data)
    }

    override fun setModelName(string: String?) {
        findAddFragment()?.setModelName(string)
    }

    override fun setEditData(data: CarEntity) {
        findAddFragment()?.setEditData(data)
    }

    private fun findAddFragment(): AddFragment? =
        supportFragmentManager.findFragmentByTag("add") as? AddFragment
}





package com.example.composevsxml.xml_ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.composevsxml.DataModel
import com.example.composevsxml.MainViewModel
import com.example.composevsxml.R
import com.example.composevsxml.xml_ui.MultiViewAdapter.OnItemClickListener
import kotlinx.coroutines.launch

class XMLActivity : AppCompatActivity() {

    private val viewModel = MainViewModel()

    private lateinit var adapter: MultiViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xml)
        runXML()
    }

    private fun runXML() {

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        val layoutManager = LinearLayoutManager(this)

        adapter = MultiViewAdapter(object: OnItemClickListener {

            override fun onItemClick(item: DataModel) {
                val modalBottomSheet = XMLModalBottomSheet()
                val bundle = Bundle()
                bundle.putSerializable("data", item)
                modalBottomSheet.arguments = bundle
                modalBottomSheet.show(supportFragmentManager, "ModalBottomSheet")
            }

            override fun onItemDeleteClick(item: DataModel) {
                viewModel.deleteData(item)
            }
        })

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewModelStateFlow.collect { state ->
                    Log.d("XMLActivity", "state: $state")
                    adapter.updateData(state)
                }
            }
        }

        findViewById<View>(R.id.fabAdd).setOnClickListener {
            val position = layoutManager.findFirstCompletelyVisibleItemPosition()
            viewModel.addItemToPosition(position + 1,
                DataModel.generateDummyData(id = adapter.getBiggestId() + 1)
            )
        }

    }

}
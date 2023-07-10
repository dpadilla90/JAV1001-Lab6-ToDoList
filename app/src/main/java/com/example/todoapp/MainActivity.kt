package com.example.todoapp

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.todoapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var tasks: ArrayList<String>
    private lateinit var adapter: ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

            // Inflate the layout using data binding
            binding = ActivityMainBinding.inflate(layoutInflater)

            // Get the root view from the binding object
            val view = binding.root

            // Set the content view to the root view
            setContentView(view)

            // Initialize the tasks list and adapter
            tasks = ArrayList()
            adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, tasks)

            // Set the adapter to the ListView using binding
            binding.listViewTasks.adapter = adapter

            // Set a click listener for the button using binding
            binding.buttonAdd.setOnClickListener {
                addTask()
            }
    }
    private fun addTask() {
        // Get the task from the EditText using binding and trim any leading/trailing spaces
        val task = binding.editTextTask.text.toString().trim()

        if (task.isNotEmpty()) {
            // Add the task to the list
            tasks.add(task)

            // Notify the adapter of the data change
            adapter.notifyDataSetChanged()

            // Clear the input in the EditText
            binding.editTextTask.text.clear()
        }

    }
}
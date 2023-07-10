package com.example.todoapp

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.todoapp.databinding.ActivityMainBinding
import com.example.todoapp.R.drawable.list_item_selector


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

            binding.listViewTasks.choiceMode = ListView.CHOICE_MODE_SINGLE
            binding.listViewTasks.selector = getDrawable(list_item_selector)


        // Set a click listener for the button using binding
            binding.buttonAdd.setOnClickListener {
                addTask()
            }
            binding.buttonClear.setOnClickListener {
                clearTasks()
            }

            binding.buttonDelete.setOnClickListener {
                deleteTask()
            }
    }
    /**
     * Function to add a task to the list
     */
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
    /**
     * Function to clear all tasks from the list
     */
    private fun clearTasks() {
        tasks.clear()
        adapter.notifyDataSetChanged()
    }


    /**
     * Function to delete the selected task from the list
     */
    private fun deleteTask() {
        val selectedItem = binding.listViewTasks.checkedItemPosition
        if (selectedItem != -1) {
            tasks.removeAt(selectedItem)
            adapter.notifyDataSetChanged()
            binding.listViewTasks.clearChoices() // Clear the selection
        }
    }

}
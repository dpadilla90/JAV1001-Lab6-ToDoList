package com.example.todoapp

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.todoapp.databinding.ActivityMainBinding
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.DrawableCompat


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
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_checked, tasks)

        // Set the adapter to the ListView using binding
        binding.listViewTasks.adapter = adapter

        //Set choice mode for ListView
        binding.listViewTasks.choiceMode = ListView.CHOICE_MODE_MULTIPLE

        // Retrieve the list item selector drawable using AppCompatResources
        val listSelector = AppCompatResources.getDrawable(this, R.drawable.list_item_selector)
        // Apply tint to the drawable
        DrawableCompat.setTint(listSelector!!, resources.getColor(R.color.darkgray, null))
        binding.listViewTasks.selector = listSelector

        // Set a click listener for the "Add" button using binding
        binding.buttonAdd.setOnClickListener {
            addTask()
        }
         // Set a click listener for the "Clear" button using binding
        binding.buttonClear.setOnClickListener {
            clearTasks()
        }
        // Set a click listener for the "Delete" button using binding
        binding.buttonDelete.setOnClickListener {
            deleteSelectedTasks()
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
        // Clear the tasks list
        tasks.clear()

        // Notify the adapter of the data change
        adapter.notifyDataSetChanged()
    }


    /**
     * Function to delete the selected task from the list
     */
    private fun deleteSelectedTasks() {
        val checkedPositions = binding.listViewTasks.checkedItemPositions

        // Remove the selected items in reverse order
        for (i in checkedPositions.size() - 1 downTo 0) {
            val position = checkedPositions.keyAt(i)
            if (checkedPositions.valueAt(i)) {
                tasks.removeAt(position)
            }
        }

        // Clear the checked state and update the adapter
        binding.listViewTasks.clearChoices()
        adapter.notifyDataSetChanged()
    }


}
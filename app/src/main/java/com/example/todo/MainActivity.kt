package com.example.todo

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AlertDialog  // Import AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : ComponentActivity() {
    private val tasks = mutableListOf(
        Task("Task 1", "Description 1", "2024-12-01", "Pending", "12 AM"),
        Task("Task 2", "Description 2", "2024-12-02", "Completed", "12 AM")
    )

    private lateinit var taskAdapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // Find RecyclerView by its ID
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        // Initialize the RecyclerView with a LayoutManager
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize the adapter
        taskAdapter = TaskAdapter(tasks)
        recyclerView.adapter = taskAdapter

        val btnCreateTask = findViewById<Button>(R.id.btnCreateTask)
        btnCreateTask.setOnClickListener {
            showCreateTaskDialog()
        }
    }

    private fun showCreateTaskDialog() {
        // Create an AlertDialog
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_create_task, null)
        val titleEditText = dialogView.findViewById<EditText>(R.id.editTextTitle)
        val descriptionEditText = dialogView.findViewById<EditText>(R.id.editTextDescription)
        val deadlineEditText = dialogView.findViewById<EditText>(R.id.editTextDeadline)

        val builder = AlertDialog.Builder(this)
        builder.setView(dialogView)
            .setTitle("Create New Task")
            .setPositiveButton("Create") { _, _ ->
                // Get user input
                val title = titleEditText.text.toString()
                val description = descriptionEditText.text.toString()
                val deadline = deadlineEditText.text.toString()

                // If all fields are filled, create and add the task to the list
                if (title.isNotBlank() && description.isNotBlank() && deadline.isNotBlank()) {
                    val newTask = Task(title, description, deadline, "Pending", "12 AM")
                    tasks.add(newTask)
                    taskAdapter.notifyItemInserted(tasks.size - 1)  // Update RecyclerView
                    Toast.makeText(this, "Task created", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}
